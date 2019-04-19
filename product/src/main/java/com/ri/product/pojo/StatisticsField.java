package com.ri.product.pojo;

import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.domains.LongId;

import java.io.Serializable;

@Entity(table = "statistics_field")
public class StatisticsField extends LongId implements Serializable {

    private long statisticsId;

    private String fieldInDB;

    private String fieldName;

    public long getStatisticsId() {
        return statisticsId;
    }

    public StatisticsField setStatisticsId(long statisticsId) {
        this.statisticsId = statisticsId;
        return this;
    }

    public String getFieldInDB() {
        return fieldInDB;
    }

    public StatisticsField setFieldInDB(String fieldInDB) {
        this.fieldInDB = fieldInDB;
        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public StatisticsField setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }
}
