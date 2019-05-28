package com.ri.influxdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InfluxdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfluxdbApplication.class, args);
    }

}
