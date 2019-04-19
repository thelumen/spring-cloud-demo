package com.ri.product.pojo;

import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.domains.LongId;

import java.io.Serializable;

@Entity(table = "statistics")
public class Statistics extends LongId implements Serializable {

    private String name;

    private String statementSelect;

    private String statementFrom;

    private String statementWhere;

    public String getName() {
        return name;
    }

    public Statistics setName(String name) {
        this.name = name;
        return this;
    }

    public String getStatementSelect() {
        return statementSelect;
    }

    public Statistics setStatementSelect(String statementSelect) {
        this.statementSelect = statementSelect;
        return this;
    }

    public String getStatementFrom() {
        return statementFrom;
    }

    public Statistics setStatementFrom(String statementFrom) {
        this.statementFrom = statementFrom;
        return this;
    }

    public String getStatementWhere() {
        return statementWhere;
    }

    public Statistics setStatementWhere(String statementWhere) {
        this.statementWhere = statementWhere;
        return this;
    }
}
