package com.ri.eurekaclientorder.service.impl;

import com.ri.eurekaclientorder.bo.DashboardBo;
import com.ri.eurekaclientorder.dao.TestRepository;
import com.ri.eurekaclientorder.pojo.DashboardSet;
import com.ri.eurekaclientorder.service.MongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MongoDBServiceImpl implements MongoDBService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Map<String, Object> queryByDashboardSet(DashboardSet dashboardSet) {
        String createTime = "createTime";
        String payment = "payableAmount";
        String time = "time";
        // 验证查询对象
        validatorDashboardSetForMongoDB(dashboardSet);
        // 筛选结果集
        Criteria criteria = new Criteria("createTime").gte(dashboardSet.getStartTime()).lte(dashboardSet.getEndTime());
        // 筛选资源池和可用域
//        if (dashboardSet.getResourcepool() != null && !dashboardSet.getResourcepool().equals("")) {
//            criteria = criteria.and("resourcePool").is(dashboardSet.getResourcepool());
//        }
//        if (dashboardSet.getAvailablezone() != null && !dashboardSet.getAvailablezone().equals("")) {
//            criteria = criteria.and("resourcePool").is(dashboardSet.getAvailablezone());
//        }
        // 筛选 用户
        if (dashboardSet.getUserId() != null && !"".equals(dashboardSet.getUserId())) {
            criteria.and("userId").is(dashboardSet.getUserId());
        }
//        criteria
        MatchOperation match = Aggregation.match(criteria);
        // 修改结果集时间字段 方法1
//        ProjectionOperation timeProject = Aggregation.project(payment)
//                .and(StringOperators.Substr.valueOf(createTime).substring(0, Integer.valueOf(dashboardSet.getTimeDivision()))).as(time);
        // 修改结果集时间字段 方法2
        ProjectionOperation timeProject2 = Aggregation.project(payment)
                .and(DateOperators.DateToString.dateOf(createTime).toString(dashboardSet.getTimeDivision())).as(time);
        // 对结果集分组统计
        GroupOperation group = Aggregation.group(time)
                .sum(payment).as("total")
                .count().as("used");
        // 注意 group key time 会映射成 _id，所以要利用 project 阶段映射回 time
        ProjectionOperation project = Aggregation.project("total", "used")
                .and("_id").as(time).andExclude("_id");
        // 按时间排序
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.ASC, time);
        // 组装管道
        Aggregation aggregation = Aggregation.newAggregation(match, timeProject2, group, project, sortOperation);
        System.out.println(aggregation.toString());
        AggregationResults<DashboardBo> results = mongoTemplate.aggregate(aggregation, "order", DashboardBo.class);
        return validatorResponse(dashboardSet, results.getMappedResults());
    }


    private static void validatorDashboardSetForMongoDB(DashboardSet dashboardSet) {
        // 检查间隔标志
        boolean checkInterval = false;
        // 检查开始时间
        if (dashboardSet.getStartTime() == null) {
            // 不存在时设置为默认前六个月
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -6);
            dashboardSet.setStartTime(calendar.getTime());
            checkInterval = true;
        }
        // 结束时间未指定时，设置为当前时间
        if (dashboardSet.getEndTime() == null) {
            dashboardSet.setEndTime(new Date());
        }
        // 判断起止时间间隔是否大于一个月
        if (dashboardSet.getTimeDivision() == null) {
            if (checkInterval || differentDaysByMillisecond(dashboardSet.getStartTime(), dashboardSet.getEndTime()) >= 30) {
                dashboardSet.setTimeDivision("%Y-%m");
            } else if (differentDaysByMillisecond(dashboardSet.getStartTime(), dashboardSet.getEndTime()) >= 365) {
                dashboardSet.setTimeDivision("%Y");
            } else {
                dashboardSet.setTimeDivision("%Y-%m-%d");
            }
        }
    }

    private static int differentDaysByMillisecond(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
    }

    private Map<String, Object> validatorResponse(DashboardSet dashboardSet, List<DashboardBo> dashboardBoList) {
        return new HashMap<String, Object>() {{
            put("dashboardSet", dashboardSet);
            put("dashboardBoList", dashboardBoList);
        }};
    }
}
