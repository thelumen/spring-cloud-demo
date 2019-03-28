package com.ri.eurekaclientorder.dao;

import com.ri.eurekaclientorder.pojo.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestRepository extends MongoRepository<Order, String> {

    List<Order> findOrdersBy_id(String _id);
}
