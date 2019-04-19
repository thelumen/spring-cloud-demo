package com.ri.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mybatis.repository.config.EnableMybatisRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMybatisRepositories(basePackages = "com.ri.product.dao.mybatis")
@EnableMongoRepositories(basePackages = "com.ri.product.dao.mongo")
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
