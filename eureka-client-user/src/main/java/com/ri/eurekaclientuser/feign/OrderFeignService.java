package com.ri.eurekaclientuser.feign;

import com.ri.eurekaclientuser.feign.hystric.OrderFeignServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client-order", fallback = OrderFeignServiceHystric.class)
public interface OrderFeignService {

    @RequestMapping(value = "/basis/hi", method = RequestMethod.GET)
    String getHiFromOrder(@RequestParam(value = "name") String name);
}
