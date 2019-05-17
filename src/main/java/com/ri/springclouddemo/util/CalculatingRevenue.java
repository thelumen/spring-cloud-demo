package com.ri.springclouddemo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatingRevenue {

    public void calculateAnnualizedRate() {

    }

    static void calculateIncome(String regEx, String value) {
//        System.out.println(regEx);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            System.out.println("参数  "+ value +"  合法！");
        } else {
            System.out.println("参数  "+ value +"  不合法！");
        }
    }

    public static void main(String[] args) {
        String regEx1 = "([0-9]\\d*|0)(\\.\\d+)?,([0-9]\\d*|0)(\\.\\d+)?";
        String regEx2 = "([0-9]\\d*|0)(\\.\\d+)?(,([0-9]\\d*|0)(\\.\\d+)?){0,32}";
        String regEx3 = "[a-zA-Z\\d\\u4E00-\\u9FA5_\\.\\-%@\\*]+,[a-zA-Z\\d\\u4E00-\\u9FA5_\\.\\-%@\\*]+";
        String regEx4 = "[a-zA-Z\\d\\u4E00-\\u9FA5_\\.\\-%@\\*]+(,[a-zA-Z\\d\\u4E00-\\u9FA5_\\.\\-%@\\*]+[^,]){0,32}";
        String regEx5 = "\\d{4}(-[0-1]?\\d?){2}\\s[0-1]?\\d?(:[0-5]?\\d?){2}";
        String regEx6 = "\\d{4}(-[0-1]?\\d?){2}\\s[0-1]?\\d?(:[0-5]?\\d?){2},\\d{4}(-[0-1]?\\d?){2}\\s[0-1]?\\d?(:[0-5]?\\d?){2}";
        String regEx7 = "([0-9]\\d*|0)(\\.\\d+)?";
        String regEx8 = "[a-zA-Z\\d\\u4E00-\\u9FA5_\\.\\-%@\\*]+";
//        String value = "2019-3-6 11:11:11";
        String[] value = {
                "2019-3-6 11:11:101",
                ",2019-03-6 11:1:11",
                "2019-3-6 11:01:1,2019-3-6 11:01:1",
                "2015-3-0 1:11:11,",
                "",
        };
        for (String s : value) {
            calculateIncome(regEx6, s);
        }
        String test5 = "qwe";
        System.out.println(Arrays.toString(test5.split(",")));
    }
}
