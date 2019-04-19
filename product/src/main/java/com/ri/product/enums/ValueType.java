package com.ri.product.enums;

public enum ValueType {
    VALUE_TYPE_INT("int", ""),
    VALUE_TYPE_STRING("string", ""),
    VALUE_TYPE_STRINGLIST("stringList", ""),
    VALUE_TYPE_INTLIST("intList", ""),
    VALUE_TYPE_FUNCTION("function", ""),
    VALUE_TYPE_BEWTEEN_INT("betweenInt", ""),
    VALUE_TYPE_BETWEEN_STRING("betweenString", ""),
    VALUE_TYPE_BETWEEN_FUNCTION("betweenFunction", ""),
    VALUE_TYPE_FIELD("field", ""),
    VALUE_TYPE_CONSTANT("constant", ""),
    ;

    String type;
    String regEx;

    ValueType(String type, String regEx) {
        this.type = type;
        this.regEx = regEx;
    }

    public String getType() {
        return type;
    }

    public ValueType setType(String type) {
        this.type = type;
        return this;
    }

    public String getRegEx() {
        return regEx;
    }

    public ValueType setRegEx(String regEx) {
        this.regEx = regEx;
        return this;
    }
}
