package com.ri.product.service.impl;

import com.ri.product.bo.StatisticsBO;
import com.ri.product.dao.mongo.DashboardStatementRepository;
import com.ri.product.dao.mybatis.InstanceRepository;
import com.ri.product.dao.mybatis.StatisticsRepository;
import com.ri.product.dao.mybatis.VmInstanceRepository;
import com.ri.product.pojo.*;
import com.ri.product.service.MySQLService;
import com.ri.product.util.DateUtil;
import com.ri.product.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ri.product.constant.Constant.*;
import static com.ri.product.enums.ValueType.*;
import static com.ri.product.util.MatcherUtil.match;

@Service
public class MySQLServiceImpl implements MySQLService {

    @Autowired
    VmInstanceRepository vmInstanceRepository;

    @Autowired
    InstanceRepository instanceRepository;

    @Autowired
    StatisticsRepository statisticsRepository;

    @Autowired
    DashboardStatementRepository dashboardStatementRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Map<String, Object> saveDashboardStatement(DashboardStatement dashboardStatement) {
        return new HashMap<String, Object>() {{
            put("result", dashboardStatementRepository.save(dashboardStatement));
        }};
    }

    public Map<String, Object> deleteDashboardStatement(DashboardStatement dashboardStatement) {
        dashboardStatementRepository.delete(dashboardStatement);
        return new HashMap<String, Object>() {{
            put("result", "maybe success");
        }};
    }

    public Map<String, Object> queryDashboardStatement(DashboardStatement dashboardStatement) {
        String userId = null;
        if (dashboardStatement.getUserId() != null && !dashboardStatement.getUserId().equals("")) {
            userId = dashboardStatement.getUserId();
        }
        Query query = new Query();
        if (userId != null && !userId.equals(""))
            query.addCriteria(Criteria.where("userId").is(userId));
        List<DashboardStatement> dashboardStatements = mongoTemplate.find(query, DashboardStatement.class);
        return new HashMap<String, Object>() {{
            put("result", dashboardStatements);
        }};
    }

    public Map<String, Object> getDashboardStatementByUserId(DashboardStatement dashboardStatement) {
        String userId = null;
        if (dashboardStatement.getUserId() != null && !dashboardStatement.getUserId().equals("")) {
            userId = dashboardStatement.getUserId();
        }
        Query query = new Query();
        if (userId != null && !userId.equals("")) {
            query.addCriteria(Criteria.where("userId").is(userId));
        } else {
            return new HashMap<String, Object>() {{
                put("error", "miss userId ");
            }};
        }
        List<DashboardStatement> dashboardStatements = mongoTemplate.find(query, DashboardStatement.class);
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> map;
        for (DashboardStatement dashboardStatement1 : dashboardStatements) {
            map = testInsertStatement(dashboardStatement1);
            if (map.containsKey("error")) {
                return map;
            }
            resultList.add(map);
        }
        return new HashMap<String, Object>() {{
            put("result", resultList);
        }};
    }

    @Override
    public Map<String, Object> testInsertStatement(DashboardStatement dashboardStatement) {
        if (dashboardStatement.getStatisticsName() == null || dashboardStatement.getStatisticsName().equals(""))
            return errorMap("缺少统计名");
        // 根据统计名获取查询的基本组成信息
        List<StatisticsBO> statisticsBOList = statisticsRepository.queryByStatementName(new Statistics().setName(dashboardStatement.getStatisticsName()));
        if (statisticsBOList == null || statisticsBOList.size() == 0)
            return errorMap("缺少统计信息");
        StatisticsBO statisticsBO = statisticsBOList.get(0);
        if (statisticsBO.getStatementFrom() == null || statisticsBO.getStatementFrom().equals(""))
            return errorMap("统计信息 From 存在错误");
        if (statisticsBO.getStatementSelect() == null || statisticsBO.getStatementSelect().equals(""))
            return errorMap("统计信息 Select 存在错误");
        if (statisticsBO.getStatementWhere() == null || statisticsBO.getStatementWhere().equals(""))
            return errorMap("统计信息 Where 存在错误");
        Statistics statistics = new Statistics()
                .setStatementFrom(statisticsBO.getStatementFrom())
                .setStatementSelect(statisticsBO.getStatementSelect())
                .setStatementWhere(statisticsBO.getStatementWhere());
        // 制作字段过滤集合
        Set<String> fieldSet = new HashSet<>();
        statisticsBOList.forEach(statisticsBO1 -> fieldSet.add(statisticsBO1.getFieldInDB()));
        List<String> selectList = new ArrayList<>();
        List<String> whereList = new ArrayList<>();
        List<String> groupList = new ArrayList<>();
        Map<String, Object> completeStatement;
        Map<String, Object> mapping = new HashMap<>();
        List<String> index = new ArrayList<>();
        List<String> indexY = new ArrayList<>();
        for (Statement statement : dashboardStatement.getStatements()) {
            if (statement.getOrder() == null) {
                return errorMap("error order in statement");
            }
            switch (statement.getOrder()) {
                case ORDER_SELECT:
                    completeStatement = selectHandle(statement, fieldSet);
                    if (completeStatement.containsKey("error"))
                        return errorMap(completeStatement.get("error"));
                    // 添加到SQL结果映射
                    selectList.add(String.valueOf(completeStatement.get("completeStatement")));
                    // 添加到展示结果映射
                    mapping.putAll((HashMap<String, Object>) completeStatement.get("mapping"));
                    // 添加展示结果目录
                    index.addAll((ArrayList<String>) completeStatement.get("index"));
                    break;
                case ORDER_WHERE:
                    completeStatement = whereHandle(statement, false, fieldSet);
                    if (completeStatement.containsKey("error"))
                        return errorMap(completeStatement.get("error"));
                    whereList.add(String.valueOf(completeStatement.get("completeStatement")));
                    break;
                case ORDER_GROUP:
                    completeStatement = groupHandle(statement, fieldSet);
                    if (completeStatement.containsKey("error"))
                        return errorMap(completeStatement.get("error"));
                    // 添加到展示结果映射
                    mapping.putAll((Map<String, Object>) completeStatement.get("mapping"));
                    // 添加展示结果目录
                    indexY.addAll((ArrayList<String>) completeStatement.get("index"));
                    // 添加到分组
                    groupList.add(String.valueOf(completeStatement.get("completeStatementForGroup")));
                    // 添加到SQL结果映射
                    selectList.add(String.valueOf(completeStatement.get("completeStatementForSelect")));
                    break;
            }
        }
        List<HashMap<String, Object>> vmInstanceMapList = vmInstanceRepository.queryByStatement(selectList, whereList, groupList, statistics);
        return new HashMap<String, Object>() {{
            put("list", vmInstanceMapList);
            put("mapping", mapping);
            put("indexY", indexY);
            put("index", index);
        }};
    }

    /**
     * select 处理器
     *
     * @param statement 语句对象
     * @param fieldSet  字段过滤集合
     * @return 封装结果 error：错误信息 completeStatement：已完成的语句 index：映射目录 mapping：映射集
     */
    private Map<String, Object> selectHandle(Statement statement, Set<String> fieldSet) {
        StringBuilder stringBuilder = new StringBuilder();
        if ((statement.getField() == null && statement.getFunctionList() == null) || (statement.getField().equals("") && statement.getFunctionList().size() == 0)) {
            return errorMap("error field in select");
        }
        Map<String, String> mapping = new HashMap<>();
        List<String> index = new ArrayList<>();
        if (VALUE_TYPE_FIELD.getType().equals(statement.getValueType())) {
            if (!fieldSet.contains(statement.getField()))
                return errorMap("field illegal in select");
//            if (!match(VALUE_TYPE_STRING.getRegEx(), statement.getValue()))
//                return errorMap("value illegal in select");
            stringBuilder.append(statement.getField());
            if (statement.getValue() != null && !statement.getValue().equals("")) {
                mapping.put(statement.getField(), statement.getValue());
            } else {
                mapping.put(statement.getField(), statement.getField());
            }
            index.add(statement.getField());
        } else if (VALUE_TYPE_FUNCTION.getType().equals(statement.getValueType())) {
            Function function = statement.getFunctionList().get(0);
            String alias = getAliasFromFunction(function);
            Map<String, Object> map = functionHandle(function, fieldSet);
            if (map.containsKey("error")) {
                return map;
            }
            stringBuilder.append(map.get("completeFunction")).append(" AS ").append(alias);
            if (statement.getValue() != null && !statement.getValue().equals("")) {
                mapping.put(alias, statement.getValue());
            } else {
                mapping.put(alias, alias);
            }
            index.add(alias);
        } else {
            return errorMap("error value type in select order");
        }
        return new HashMap<String, Object>() {{
            put("completeStatement", stringBuilder.toString());
            put("mapping", mapping);
            put("index", index);
        }};
    }

    /**
     * where 指令处理器
     *
     * @param statement      语句对象
     * @param firstStatement 是否是首句？是，不处理连接词：不是，处理连接词
     * @param fieldSet       字段过滤集合
     * @return 封装结果 error：错误信息 completeStatement：已完成的语句
     */
    private Map<String, Object> whereHandle(Statement statement, boolean firstStatement, Set<String> fieldSet) {
        StringBuilder stringBuilder = new StringBuilder();
        // 判断是否是首句
        if (!firstStatement) {
            // 否，拼接连接词
            if (!LINK.containsKey(statement.getLink()))
                return errorMap("error link in where");
            stringBuilder.append(LINK.get(statement.getLink())).append(" ");
        }
        // 判断连接词之后是否有子句
        if (statement.getStatementList() != null && statement.getStatementList().size() > 0) {
            // 拼接子句
            stringBuilder.append("( ");
            boolean firstClause = true;
            // 剔除子句首句连接词
            for (Statement clause : statement.getStatementList()) {
                // 回调获取完整句子
                Map<String, Object> clauseMap = whereHandle(clause, firstClause, fieldSet);
                if (clauseMap.containsKey("error"))
                    return clauseMap;
                stringBuilder.append(clauseMap.get("completeStatement"));
                firstClause = false;
            }
            stringBuilder.append(") ");
            // 子句处理后立即结束
            return new HashMap<String, Object>() {{
                put("completeStatement", stringBuilder.toString());
            }};
        }
        // 拼接字段
        if (!fieldSet.contains(statement.getField()))
            return errorMap("field illegal in where");
        stringBuilder.append(statement.getField()).append(" ");

        // 拼接操作符
        if (statement.getOperate() != null) {
            if (OPERATE.containsKey(statement.getOperate())) {
                stringBuilder.append(OPERATE.get(statement.getOperate())).append(" ");
            } else {
                return errorMap("error operate");
            }
        }
        // 拼接数据
        if (VALUE_TYPE_INT.getType().equals(statement.getValueType())) {
            if (!match(VALUE_TYPE_INT.getRegEx(), statement.getValue()))
                return errorMap("value illegal in where");
            stringBuilder.append(statement.getValue()).append(" ");
        } else if (VALUE_TYPE_STRING.getType().equals(statement.getValueType())) {
            if (!match(VALUE_TYPE_STRING.getRegEx(), statement.getValue()))
                return errorMap("value illegal in where");
            stringBuilder.append("'").append(statement.getValue()).append("' ");
        } else if (VALUE_TYPE_INTLIST.getType().equals(statement.getValueType())) {
            if (!match(VALUE_TYPE_INTLIST.getRegEx(), statement.getValue()))
                return errorMap("value illegal in where");
            stringBuilder.append("(").append(statement.getValue()).append(") ");
        } else if (VALUE_TYPE_STRINGLIST.getType().equals(statement.getValueType())) {
            if (!match(VALUE_TYPE_STRINGLIST.getRegEx(), statement.getValue()))
                return errorMap("value illegal in where");
            stringBuilder.append("(");
            String[] valueArray = statement.getValue().split(",");
            for (String s : valueArray) {
                stringBuilder.append("'").append(s).append("',");
            }
            stringBuilder.append(") ");
        } else if (VALUE_TYPE_FUNCTION.getType().equals(statement.getValueType())) {
            Map<String, Object> map = functionHandle(statement.getFunctionList().get(0), fieldSet);
            if (map.containsKey("error"))
                return map;
            stringBuilder.append(map.get("completeFunction"));
        } else if (VALUE_TYPE_BEWTEEN_INT.getType().equals(statement.getValueType())) {
            if (!match(VALUE_TYPE_BEWTEEN_INT.getRegEx(), statement.getValue()))
                return errorMap("value illegal in where");
            String[] valueArray = statement.getValue().split(",");
            stringBuilder.append(valueArray[0]).append(" AND ").append(valueArray[1]);
        } else if (VALUE_TYPE_BETWEEN_STRING.getType().equals(statement.getValueType())) {
            if (!match(VALUE_TYPE_BETWEEN_STRING.getRegEx(), statement.getValue()))
                return errorMap("value illegal in where");
            String[] valueArray = statement.getValue().split(",");
            stringBuilder.append(valueArray[0]).append("' AND '").append(valueArray[1]);
        } else if (VALUE_TYPE_BETWEEN_FUNCTION.getType().equals(statement.getValueType())) {
            Map<String, Object> map = functionHandle(statement.getFunctionList().get(0), fieldSet);
            if (map.containsKey("error"))
                return map;
            stringBuilder.append(map.get("completeFunction"))
                    .append(" AND ");
            map = functionHandle(statement.getFunctionList().get(1), fieldSet);
            if (map.containsKey("error"))
                return map;
            stringBuilder.append(map.get("completeFunction"));
        } else {
            return errorMap("error value type");
        }
        return new HashMap<String, Object>() {{
            put("completeStatement", stringBuilder.toString());
        }};
    }

    /**
     * 方法处理器
     *
     * @param function 方法对象
     * @param fieldSet 字段过滤集合
     * @return 封装结果 error：错误信息 completeStatement：已完成的语句
     */
    private Map<String, Object> functionHandle(Function function, Set<String> fieldSet) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ").append(function.getFunctionName()).append("( ");
        int size = function.getParameterType().size();
        int functionCount = 0;
        for (int i = 0; i < size; i++) {
            if (VALUE_TYPE_INT.getType().equals(function.getParameterType().get(i))) {
                if (!match(VALUE_TYPE_INT.getRegEx(), function.getParameter().get(i - functionCount)))
                    return errorMap("value illegal in function");
                stringBuilder.append(function.getParameter().get(i - functionCount));
            } else if (VALUE_TYPE_STRING.getType().equals(function.getParameterType().get(i))) {
                if (!match(VALUE_TYPE_STRING.getRegEx(), function.getParameter().get(i - functionCount)))
                    return errorMap("value illegal in function");
                stringBuilder.append("'").append(function.getParameter().get(i - functionCount)).append("'");
            } else if (VALUE_TYPE_CONSTANT.getType().equals(function.getParameterType().get(i))) {
                if (!match(VALUE_TYPE_CONSTANT.getRegEx(), function.getParameter().get(i - functionCount)))
                    return errorMap("value illegal in function");
                stringBuilder.append(function.getParameter().get(i - functionCount));
            } else if (VALUE_TYPE_FIELD.getType().equals(function.getParameterType().get(i))) {
                if (!fieldSet.contains(function.getParameter().get(i - functionCount))) {
                    return errorMap("field illegal in function");
                }
                stringBuilder.append(function.getParameter().get(i - functionCount));
            } else if (VALUE_TYPE_FUNCTION.getType().equals(function.getParameterType().get(i))) {
                Map<String, Object> map = functionHandle(function.getFunctionList().get(functionCount), fieldSet);
                if (map.containsKey("error"))
                    return map;
                stringBuilder.append(map.get("completeFunction"));
                functionCount++;
            }
            if (i != (size - 1)) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(") ");
        return new HashMap<String, Object>() {{
            put("completeFunction", stringBuilder.toString());
        }};
    }

    /**
     * group 指令处理器
     *
     * @param statement 语句对象
     * @param fieldSet  字段过滤集合
     * @return 封装结果 error：错误信息
     * completeStatementForGroup：用于 group 的完成语句
     * completeStatementForSelect：用于 select 的完成语句
     * index：映射目录
     * mapping：映射集
     */
    private Map<String, Object> groupHandle(Statement statement, Set<String> fieldSet) {
        StringBuilder completeStatementForGroup = new StringBuilder();
        StringBuilder completeStatementForSelect = new StringBuilder();
        Map<String, String> mapping = new HashMap<>();
        List<String> index = new ArrayList<>();
        if (VALUE_TYPE_FIELD.getType().equals(statement.getValueType())) {
            if (!fieldSet.contains(statement.getField()))
                return errorMap("field illegal in group");
            if (!match(VALUE_TYPE_INT.getRegEx(), statement.getValue()))
                return errorMap("value illegal in group");
            completeStatementForGroup.append(statement.getField());
            // 添加别名映射关系
            if (statement.getValue() != null && !statement.getValue().equals("")) {
                mapping.put(statement.getField(), statement.getValue());
            } else {
                mapping.put(statement.getField(), statement.getField());
            }
            // 添加映射目录
            index.add(statement.getField());
            completeStatementForSelect.append(statement.getField());
        } else if (VALUE_TYPE_FUNCTION.getType().equals(statement.getValueType())) {
            Map<String, Object> map = functionHandle(statement.getFunctionList().get(0), fieldSet);
            if (map.containsKey("error"))
                return map;
            completeStatementForGroup.append(map.get("completeFunction"));
            Function function = statement.getFunctionList().get(0);
            String alias = getAliasFromFunction(function);
            map = functionHandle(function, fieldSet);
            if (map.containsKey("error"))
                return map;
            completeStatementForSelect.append(map.get("completeFunction")).append(" AS ").append(alias);
            // 添加映射目录
            if (statement.getValue() != null && !statement.getValue().equals("")) {
                mapping.put(alias, statement.getValue());
            } else {
                mapping.put(alias, alias);
            }
            // 添加映射目录
            index.add(alias);
        }
        return new HashMap<String, Object>() {{
            put("completeStatementForGroup", completeStatementForGroup.toString());
            put("completeStatementForSelect", completeStatementForSelect.toString());
            put("mapping", mapping);
            put("index", index);
        }};
    }

    /**
     * 从 function 类中获取 alias （别名）
     *
     * @param function 方法对象
     * @return alias （别名）
     */
    private String getAliasFromFunction(Function function) {
        String alias = function.getFunctionName() + "_" + function.getParameter().get(0);
        alias = alias.replace(".", "_");
        return alias;
    }

    /**
     * 返回含错误信息的map
     *
     * @param msg 信息
     * @return 含有 error key 的 map
     */
    private static Map<String, Object> errorMap(Object msg) {
        return new HashMap<String, Object>() {{
            put("error", msg);
        }};
    }

    @Override
    public long insertTestValue() {
        int count = 900 * 1000;
        long idBase = 100000000100029L;
        int vmInstanceServiceType = 1001;
        String instanceStatus = "running";
        String pool1 = "pool1";
        String pool2 = "pool2";
        String az1 = "az1";
        String az2 = "az2";
        List<Instances> instancesList = new ArrayList<>(count);
        List<VmInstance> vmInstanceList = new ArrayList<>(count);
        int endCount = 1000 * 1000;
        Date dateEnd = DateUtil.getDateFromNow(Calendar.MINUTE, -endCount);

        for (int i = 1; i < count; i++) {
            Date date = DateUtil.getDateFrom(dateEnd, Calendar.MINUTE, -i);
            Instances instances = new Instances()
                    .setInstanceId(idBase + i).setCreateDate(date)
                    .setServiceType(vmInstanceServiceType)
                    .setInstanceStatus(instanceStatus);
            if (i % 2 == 0) {
                instances.setPool(pool2);
            } else {
                instances.setPool(pool1);
            }
            if (i % 3 == 0) {
                instances.setAvailableZone(az2);
            } else {
                instances.setAvailableZone(az1);
            }
            instancesList.add(instances);
            VmInstance vmInstance = new VmInstance()
                    .setInstanceId(idBase + i)
                    .setCpu(Random.getRandom(1, 10))
                    .setMemory(Random.getRandom(8, 16));
            vmInstanceList.add(vmInstance);
        }
        Date startDate = new Date();
//        instanceRepository.save(instancesList);
//        vmInstanceRepository.save(vmInstanceList);
        return (new Date().getTime() - startDate.getTime()) / 1000 / 60;
    }
}
