package com.ri.product.enums;

public enum ValueType {
    VALUE_TYPE_INT("int", "[0-9\\.]{1,32}"),
    VALUE_TYPE_STRING("string", "[a-zA-Z0-9\\u4E00-\\u9FA5_\\.\\-%*@]{1,32}"),
    VALUE_TYPE_STRINGLIST("stringList", "[a-zA-Z0-9\\u4E00-\\u9FA5_\\.\\-,]{1,32}"),
    VALUE_TYPE_INTLIST("intList", "[0-9\\.,]{1,32}"),
    VALUE_TYPE_FUNCTION("function", ""),
    VALUE_TYPE_BEWTEEN_INT("betweenInt", "[0-9\\.,]{1,32}"),
    VALUE_TYPE_BETWEEN_STRING("betweenString", "[a-zA-Z0-9\\u4E00-\\u9FA5_\\.\\-,]{1,32}"),
    VALUE_TYPE_BETWEEN_FUNCTION("betweenFunction", ""),
    VALUE_TYPE_FIELD("field", ""),
    VALUE_TYPE_CONSTANT("constant", "[a-zA-Z0-9\\u4E00-\\u9FA5_\\.\\-\\s]{1,32}"),
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
