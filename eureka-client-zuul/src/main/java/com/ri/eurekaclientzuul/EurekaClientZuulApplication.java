package com.ri.eurekaclientzuul;

import com.ri.eurekaclientzuul.filter.AccessPasswordFilter;
import com.ri.eurekaclientzuul.filter.AccessUserNameFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class EurekaClientZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientZuulApplication.class, args);
    }

    @Bean
    public AccessUserNameFilter accessUserNameFilter() {
        return new AccessUserNameFilter();
    }

    @Bean
    public AccessPasswordFilter accessPasswordFilter() {
        return new AccessPasswordFilter();
    }
}
