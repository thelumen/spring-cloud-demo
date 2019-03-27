package com.ri.eurekaclientuser.feign.hystric;

import com.ri.eurekaclientuser.feign.OrderFeignService;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignServiceHystric implements OrderFeignService {
    @Override
    public String getHiFromOrder(String name) {
        return "sorry " + name + " , The Service has broken.";
    }
}
