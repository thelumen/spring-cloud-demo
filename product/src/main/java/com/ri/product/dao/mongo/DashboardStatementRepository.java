package com.ri.product.dao.mongo;

import com.ri.product.pojo.DashboardStatement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DashboardStatementRepository extends MongoRepository<DashboardStatement, String> {

}
