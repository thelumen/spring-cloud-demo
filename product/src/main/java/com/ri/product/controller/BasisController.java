package com.ri.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.ri.product.pojo.DashboardStatement;
import com.ri.product.service.MySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/basis")
public class BasisController {

    @Autowired
    MySQLService mySQLService;

    @RequestMapping("/hi")
    public String getHiFromOrder(@RequestParam(value = "name") String name) {
        return " .Come from user basis controller" + name;
    }

    /**
     *  添加测试数据
     *
     * @return 耗时
     */
    @RequestMapping(value = "/insertTestValue", method = RequestMethod.GET)
    public String insertTestValue() {
        return "success. using time: " + mySQLService.insertTestValue();
    }

    @RequestMapping(value = "testInsertStatement", method = RequestMethod.POST)
    public Map<String, Object> testInsertStatement(@RequestBody DashboardStatement dashboardStatement) {
        return mySQLService.testInsertStatement(dashboardStatement);
    }

    @RequestMapping(value = "testInsertStatementWithJson", method = RequestMethod.POST)
    public Map<String, Object> testInsertStatementWithJson(@RequestBody String jsonStr) {
        DashboardStatement dashboardStatement = JSONObject.parseObject(jsonStr, DashboardStatement.class);
        return mySQLService.testInsertStatement(dashboardStatement);
    }

    @RequestMapping(value = "saveDashboardStatement", method = RequestMethod.POST)
    public Map<String, Object> saveDashboardStatement(@RequestBody String jsonStr) {
        DashboardStatement dashboardStatement = JSONObject.parseObject(jsonStr, DashboardStatement.class);
        return mySQLService.saveDashboardStatement(dashboardStatement);
    }

    @RequestMapping(value = "queryDashboardStatement", method = RequestMethod.POST)
    public Map<String, Object> queryDashboardStatement(@RequestBody String jsonStr) {
        DashboardStatement dashboardStatement = JSONObject.parseObject(jsonStr, DashboardStatement.class);
        return mySQLService.queryDashboardStatement(dashboardStatement);
    }

    @RequestMapping(value = "getDashboardStatementByUserId", method = RequestMethod.POST)
    public Map<String, Object> getDashboardStatementByUserId(@RequestBody String jsonStr) {
        DashboardStatement dashboardStatement = JSONObject.parseObject(jsonStr, DashboardStatement.class);
        return mySQLService.getDashboardStatementByUserId(dashboardStatement);
    }

    @RequestMapping(value = "deleteDashboardStatement", method = RequestMethod.POST)
    public Map<String, Object> deleteDashboardStatement(@RequestBody String jsonStr) {
        DashboardStatement dashboardStatement = JSONObject.parseObject(jsonStr, DashboardStatement.class);
        return mySQLService.deleteDashboardStatement(dashboardStatement);
    }
}
