package com.ri.springclouddemo.util;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

public class ObjectWrapper {
    /**
     * 将 json 中的数据按照名称填写到 pojo 中
     *
     * @param t
     * @param param
     * @param <T>
     * @return
     */
    public static <T> T instantiaObject(Class t, JSONObject param) {
        T obj = null;
        try {
            obj = (T) t.newInstance();
            Class clz = obj.getClass();
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                Method m = obj.getClass().getMethod("set" + getMethodName(field.getName()), field.getType());
                if (field.getName().equals("createTime")) {
                    m.invoke(obj, new Date());
                }
                if ("java.lang.Long".equals(field.getType().getName())) {
                    m.invoke(obj, param.getLong(field.getName()));
                } else if ("java.lang.Integer".equals(field.getType().getName())) {
                    m.invoke(obj, param.getInteger(field.getName()));
                } else if ("java.lang.String".equals(field.getType().getName())) {
                    m.invoke(obj, param.getString(field.getName()));
                } else if ("java.lang.Double".equals(field.getType().getName())) {
                    m.invoke(obj, param.getDouble(field.getName()));
                } else if ("java.util.Date".equals(field.getType().getName())) {
                    m.invoke(obj, param.getDate(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 获取类的属性，加工后输出
     *
     * @param c
     */
    public static void getAllField(Class c) {
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(stringFormat(field.getName()));
        }

    }

    /**
     * 加工方法
     *
     * @param string
     * @return
     */
    private static String stringFormat(String string) {
        return String.format(" AS %s,", string);
    }

    /**
     * 把一个字符串的第一个字母大写、效率是最高的
     *
     * @param fieldName
     * @return
     */
    private static String getMethodName(String fieldName) {
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
