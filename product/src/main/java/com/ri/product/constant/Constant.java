package com.ri.product.constant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Constant {


    // 指令类型
    public static final String ORDER_SELECT = "select";
    public static final String ORDER_WHERE = "where";
    public static final String ORDER_GROUP = "group";

    // 连接词类型
    public static final Map<String, String> LINK = new HashMap<String, String>() {{
        put("AND", "AND");
        put("and", "AND");
        put("OR", "OR");
        put("or", "OR");

    }};
    // 操作类型
    public static final Map<String, String> OPERATE = new HashMap<String, String>() {{
        put("lt", "<");
        put("gt", ">");
        put("ge", ">=");
        put("le", "<=");
        put("ltgt", "!=");
        put("=", "=");
        put("IN", "IN");
        put("in", "IN");
        put("is null", "IS NULL");
        put("IS NULL", "IS NULL");
        put("IS NOT NULL", "IS NOT NULL");
        put("is not null", "IS NOT NULL");
        put("between", "BETWEEN");
        put("BETWEEN", "BETWEEN");
    }};

    // 方法类型
    public static final Set<String> FUNCATION = new HashSet<String>() {{

    }};


}
