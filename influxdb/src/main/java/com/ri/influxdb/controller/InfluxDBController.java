package com.ri.influxdb.controller;

import com.ri.influxdb.server.InfluxDBTestServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("influxDB")
public class InfluxDBController {
    @Autowired
    private InfluxDBTestServer influxDBTestServer;

    @PostMapping("createDB")
    public Map<String, Object> testCreateDB() {
        return influxDBTestServer.createDB();
    }
}

