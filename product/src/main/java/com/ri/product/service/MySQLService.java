package com.ri.product.service;

import com.ri.product.pojo.DashboardStatement;

import java.util.Map;

public interface MySQLService {

    long insertTestValue();

    Map<String,Object> testInsertStatement(DashboardStatement dashboardStatement);
}
