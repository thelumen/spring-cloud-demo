package com.ri.product.pojo;

import java.util.Arrays;
import java.util.List;

public class Statement {
    // 指令 如: select where
    private String order;
    // 数据
    private String value;
    // 数据类型
    private String valueType;
    // 字段
    private String field;
    // 操作符
    private String operate;
    // 连接符
    private String link;
    // 嵌套语句
    private List<Statement> statementList;
    // 方法语句
    private List<Function> functionList;

    public String getOrder() {
        return order;
    }

    public Statement setOrder(String order) {
        this.order = order;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Statement setValue(String value) {
        this.value = value;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public Statement setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getField() {
        return field;
    }

    public Statement setField(String field) {
        this.field = field;
        return this;
    }

    public String getOperate() {
        return operate;
    }

    public Statement setOperate(String operate) {
        this.operate = operate;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Statement setLink(String link) {
        this.link = link;
        return this;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public Statement setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
        return this;
    }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public Statement setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
        return this;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "order='" + order + '\'' +
                ", value='" + value + '\'' +
                ", valueType='" + valueType + '\'' +
                ", field='" + field + '\'' +
                ", operate='" + operate + '\'' +
                ", link='" + link + '\'' +
                ", statementList=" + Arrays.toString(statementList.toArray()) +
                ", functionList=" + Arrays.toString(functionList.toArray()) +
                '}';
    }
}
