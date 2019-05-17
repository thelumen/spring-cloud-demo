package com.ri.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.ri.product.pojo.DashboardStatement;
import com.ri.product.service.MySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    MySQLService mySQLService;

    @RequestMapping(value = "testInsertStatement", method = RequestMethod.POST)
    public Map<String, Object> testInsertStatement(@RequestBody DashboardStatement dashboardStatement) {
        System.out.println(dashboardStatement);
        return new HashMap<String, Object>(){{
            put("dashboardStatement", dashboardStatement);
        }};
    }

    @RequestMapping(value = "testInsertStatementWithJson", method = RequestMethod.POST)
    public Map<String, Object> testInsertStatementWithJson(@RequestBody String jsonStr) {
        DashboardStatement dashboardStatement = JSONObject.parseObject(jsonStr, DashboardStatement.class);
        System.out.println(dashboardStatement);
        return new HashMap<String, Object>(){{
            put("dashboardStatement", dashboardStatement);
        }};
    }
}
