package com.ri.eurekaclientorder.controller;

import com.ri.eurekaclientorder.pojo.DashboardSet;
import com.ri.eurekaclientorder.service.MongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public String sayHi(@RequestParam(name = "name", defaultValue = "Ri") String name) {
        String info = "Hi " + name + ", I`m " + serverName + " " + port + " controller";
        System.out.println(info);
        return info;
    }

    @RequestMapping(value = "/queryByDashboardSet", method = RequestMethod.GET)
    public Map<String, Object> queryByDashboardSet(DashboardSet dashboardSet) {
        return mongoDBService.queryByDashboardSet(dashboardSet);
    }
}
