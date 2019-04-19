package com.ri.eurekaclientorder.pojo;

public class Statement {

    private String order;

    private String value;

    private String field;

    private String operate;

    private String link;

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

    @Override
    public String toString() {
        return "Statement{" +
                "order='" + order + '\'' +
                ", value='" + value + '\'' +
                ", field='" + field + '\'' +
                ", operate='" + operate + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
