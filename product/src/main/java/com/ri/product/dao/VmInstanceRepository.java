package com.ri.product.dao;

import com.ri.product.pojo.Statistics;
import com.ri.product.pojo.VmInstance;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

public interface VmInstanceRepository extends MybatisRepository<VmInstance, Long> {

    @Query
    List<HashMap<String, Object>> queryByStatement(@Param("selectList") List<String> selectList,
                                                   @Param("whereList") List<String> whereList,
                                                   @Param("groupList") List<String> groupList,
                                                   @Param("statistics") Statistics statistics);
}
