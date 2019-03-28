package com.ri.product.controller;

import com.ri.product.service.MySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basis")
public class BasisController {

    @Autowired
    MySQLService mySQLService;

    @RequestMapping("/hi")
    public String getHiFromOrder(@RequestParam(value = "name") String name) {
        return " .Come from user basis controller" + name;
    }

    /**
     *  添加测试数据
     *
     * @return 耗时
     */
    @RequestMapping(value = "/insertTestValue", method = RequestMethod.GET)
    public String insertTestValue() {
        return "success. using time: " + mySQLService.insertTestValue();
    }
}
