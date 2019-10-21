package com.ri.influxdb.server;

import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class InfluxDBTestServer {
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    public Map<String, Object> createDB() {
        influxDBTemplate.createDatabase();
        Point p = Point.measurement("disk")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("tenant", "default")
                .addField("used", 80L)
                .addField("free", 1L)
                .build();
        influxDBTemplate.write(p);
        return new HashMap<String, Object>() {{
            put("point", p.toString());
        }};
    }
}
