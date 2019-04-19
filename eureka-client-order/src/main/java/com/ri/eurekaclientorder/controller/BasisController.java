package com.ri.eurekaclientorder.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ri.eurekaclientorder.pojo.DashboardSet;
import com.ri.eurekaclientorder.pojo.DashboardStatement;
import com.ri.eurekaclientorder.pojo.Statement;
import com.ri.eurekaclientorder.service.MongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/basis")
public class BasisController {

    @Value("${spring.application.name}")
    String serverName;
    @Value("${server.port}")
    String port;

    @Autowired
    MongoDBService mongoDBService;

    @RequestMapping("/hi")
    public String sayHi(@RequestParam(name = "name", defaultValue = "Ri") String name) {
        String info = "Hi " + name + ", I`m " + serverName + " " + port + " controller";
        System.out.println(info);
        return info;
    }

    @RequestMapping(value = "testList", method = RequestMethod.POST)
    public String testList(@RequestBody DashboardStatement dashboardStatement) {
//        JSONObject jsonObject = JSON.parseObject(jsonStr);
//        List<Statement> statements = JSONObject.parseArray(jsonObject.getString("statementList"), Statement.class);
        String info = "Hi , I`m " + serverName + " : " + port + " controller, " + dashboardStatement.toString();
        System.out.println(info);
        return info;
    }

    @RequestMapping(value = "/queryByDashboardSet", method = RequestMethod.GET)
    public Map<String, Object> queryByDashboardSet(DashboardSet dashboardSet) {
        return mongoDBService.queryByDashboardSet(dashboardSet);
    }

    @RequestMapping(value = "/queryOrderDetailByDashboardSet", method = RequestMethod.POST)
    public Map<String, Object> queryOrderDetailByDashboardSet(@RequestBody String jsonStr) {
        DashboardSet dashboardSet = JSONObject.parseObject(jsonStr, DashboardSet.class);
        return mongoDBService.queryOrderDetailByDashboardSet(dashboardSet);
    }
}
