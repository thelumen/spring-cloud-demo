package com.ri.springclouddemo.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatingRevenue {

    public void calculateAnnualizedRate() {

    }

    static void calculateIncome(String regEx, String value) {
        System.out.println(regEx);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(value);
        if(!matcher.matches()){
            System.out.println("参数 "+ value +" 的值不合法！");
        }else {
            System.out.println("参数 "+ value +" 的值合法！");
        }
    }

    public static void main(String[] args) {
        String t1 = "[0-9]{1,16}";
        String t2 = "[0-9\\.]{1,32}";
        String t3 = "[a-zA-Z0-9\\u4E00-\\u9FA5_\\.\\-]{1,32}";
        String t4 = "[0-9]{1,32}";
        String t5 = "1.";
        calculateIncome(t3,t5);
        ArrayList<Object> arrayList = new ArrayList<>();
    }
}
