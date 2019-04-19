package com.ri.product.bo;

public class StatisticsBO {

    private String name;

    private String statementSelect;

    private String statementFrom;

    private String statementWhere;

    private String fieldInDB;

    private String fieldName;

    public String getName() {
        return name;
    }

    public StatisticsBO setName(String name) {
        this.name = name;
        return this;
    }

    public String getStatementSelect() {
        return statementSelect;
    }

    public StatisticsBO setStatementSelect(String statementSelect) {
        this.statementSelect = statementSelect;
        return this;
    }

    public String getStatementFrom() {
        return statementFrom;
    }

    public StatisticsBO setStatementFrom(String statementFrom) {
        this.statementFrom = statementFrom;
        return this;
    }

    public String getStatementWhere() {
        return statementWhere;
    }

    public StatisticsBO setStatementWhere(String statementWhere) {
        this.statementWhere = statementWhere;
        return this;
    }

    public String getFieldInDB() {
        return fieldInDB;
    }

    public StatisticsBO setFieldInDB(String fieldInDB) {
        this.fieldInDB = fieldInDB;
        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public StatisticsBO setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }
}
