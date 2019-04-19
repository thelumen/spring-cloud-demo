package com.ri.product.dao.mybatis;

import com.ri.product.bo.StatisticsBO;
import com.ri.product.pojo.Statistics;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticsRepository extends MybatisRepository<Statistics, Long> {

    @Query
    List<StatisticsBO> queryByStatementName(@Param("statistics") Statistics statistics);

    @Query
    List<StatisticsBO> queryName(@Param("statistics") Statistics statistics);
}
