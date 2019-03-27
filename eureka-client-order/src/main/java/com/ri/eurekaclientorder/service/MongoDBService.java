package com.ri.eurekaclientorder.service;


import com.ri.eurekaclientorder.pojo.DashboardSet;

import java.util.Map;

public interface MongoDBService {

    Map<String, Object> queryByDashboardSet(DashboardSet dashboardSet);
}
