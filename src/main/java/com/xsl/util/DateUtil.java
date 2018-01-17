package com.xsl.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {


    /**
     * 根据当前时间和有效时长得出过期时间
     *
     * @param minutes 有效时长
     * @return
     */
    public static Date getExpiryDate(int minutes) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);

        return calendar.getTime();
    }
}
