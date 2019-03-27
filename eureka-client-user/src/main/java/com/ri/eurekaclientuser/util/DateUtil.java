package com.ri.eurekaclientuser.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date getDateFromNow(int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(field, amount);
        return calendar.getTime();
    }
}
