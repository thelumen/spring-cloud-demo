package com.ri.product.dao;

import com.ri.product.pojo.Instances;
import org.springframework.data.mybatis.repository.support.MybatisRepository;

public interface InstanceRepository extends MybatisRepository<Instances, Long> {

}
