package com.huangpw.sys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtils {

    public static final String DATE_PARTTERN = "yyyy-MM-dd hh:mm:ss";

    /**
     * 字符串转换为Date类型
     * @param msg
     * @param parttern
     * @return
     */
    public static Date stringToDate(String msg, String parttern) {
        SimpleDateFormat format = new SimpleDateFormat(parttern);
        try {
           return format.parse(msg);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
