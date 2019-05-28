package com.ri.springclouddemo;

import com.ri.springclouddemo.imp.IObjectGet;
import com.ri.springclouddemo.imp.IZhuRU;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class TestBox {

    @Test
    public void t9() {
        int i = 0;
        intAdd(i);
        System.out.println(i);
    }

    private void intAdd(int i) {
        i++;
    }
    @Test
    public void t8() {
        String[] test = new String[4];
        test[0] = "a";
        test[2] = "b";
        test[3] = "c";
        List<String> strings = Arrays.asList(test);
        System.out.println(Arrays.toString(strings.toArray()));
    }

    @Test
    public void t7() {
        Set<String> strings = new HashSet<>();
        strings.add("a");
        strings.add("b");
        System.out.println(strings.contains("a"));
    }

    @Test
    public void t6() {
        Class clazz = User.class;
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields){
            System.out.print(" "+ field.getName());
        }
//        for (Method m:methods
//             ) {
//            System.out.println(m);
//        }
    }

    @Test
    public void t5(){
        Map<String,Object> map = new HashMap<String,Object>(){{
            put("a", 1);
            put("b", 2);
            put("c", 3);
            put("d", 4);
            put("e", 5);
        }};
        map.forEach((s, o) -> System.out.println(s + " -> " + o.toString()));
    }

    @Test
    public void t4() {
        Map<String, Object> exclude = new HashMap<>();
        exclude.put("sortOrder", "");
        exclude.put("pageSize", "");
        exclude.put("pageNumber", "");
        exclude.put("page", "");
        exclude.put("pageNum", "");
        exclude.put("size", "");
        exclude.put("_", "");

        String url = "{\"id\":46,\"recordNumber\":\"1231245\",\"website\":[{\"id\":70,\"websiteNumber\":\"1231245\",\"websiteName\":\"ç\u0099¾åº¦\",\"websiteUrl\":\"https://www.baidu.com\",\"websiteContent\":\"ç»¼å\u0090\u0088é\u0097¨æ\u0088·\",\"websiteLanguage\":null,\"contentType\":\"æ\u0096°é\u0097»\",\"remark\":\"2018å¹´11æ\u009C\u008813æ\u0097¥ä¸\u008Aå\u008D\u00889ç\u0082¹32å\u0088\u0086\",\"recordInfoId\":46,\"createTime\":1542072779000,\"new\":false}]}";
        url = url.replace("\"", "");
        url = url.substring(1, url.length() - 1);
        String[] parameter = url.split(",");
        for (String s : parameter) {
            String[] sSplit = s.split(":");
            if (exclude.containsKey(sSplit[0])) {
                continue;
            }
            System.out.println(sSplit[0] + " : " + sSplit[1]);
        }
    }

    @Test
    public void t3() {
        String url = "http://192.168.1.79/customer/removeCustomer?id=07c35cf8-ab1e-4f03-b17d-054e0af8b12c";
        url = url.substring(20);
        String[] impl = url.split("/");
        String[] parameter_t = impl[impl.length - 1].split("\\?");
        StringBuilder implement = new StringBuilder();
        for (int i = 0; i < impl.length - 1; i++) {
            implement.append("/").append(impl[i]);
        }
        implement.append("/").append(parameter_t[0]);
        System.out.println(implement.toString());
        String[] parameter = parameter_t[1].split("&");
        Map<String, Object> exclude = new HashMap<>();
        exclude.put("sortOrder", "");
        exclude.put("pageSize", "");
        exclude.put("pageNumber", "");
        exclude.put("page", "");
        exclude.put("pageNum", "");
        exclude.put("size", "");
        exclude.put("_", "");
        for (String s : parameter) {
            String[] sSplit = s.split("=");
            if (exclude.containsKey(sSplit[0])) {
                continue;
            }
            if (sSplit.length < 2) {
                System.out.println(sSplit[0] + " = ");
            } else {
                System.out.println(sSplit[0] + " = " + sSplit[1]);
            }
        }
    }

    @Test
    public void t2() {
        System.out.println(calculatedUsage(10901, 48));
    }

    @Test
    public void contextLoads() {
//        test(User::setRate, new User(), 0.144d);
//        t2(User::getRate, new User().setRate(0.122d));
        List<User> users = new ArrayList<>();
        tList(users, 0.111d);
        System.out.println(users.get(0).toString());
    }

    private void test(IZhuRU<Double> iZhuRU, User u1, Double u2) {
        iZhuRU.setDouble(u1, u2);
    }

    private Object t2(IObjectGet iObjectGet, User u1) {
        System.out.println(iObjectGet.GetO(u1).getClass());
//        tDouble(iObjectGet.GetO(u1));
        return iObjectGet.GetO(u1);
    }

    private Double calculatedUsage(int total, int used) {
        Double usedRate = used / (double) total;
        return Double.parseDouble(String.format("%.3f", usedRate));
    }

    private void tDouble(double d) {
        System.out.println(d);
    }

    private void tList(List<User> userList, double d) {
        userList.add(new User().setRate(d));
    }
}
