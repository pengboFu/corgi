package com.x.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fpb on 2018/12/19.
 */
public class DateUtils {
    public static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_china = "yyyy年MM月dd日 HH:mm";
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern)
    {
        return date == null ? "" : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 给时间设置时分秒和毫秒
     */
    public static Date setDateSecond(Date date, int h, int m, int s) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, h);//时
        c.set(Calendar.MINUTE, m);//分
        c.set(Calendar.SECOND, s);//秒
        c.set(Calendar.MILLISECOND, 0);//毫秒
        Date dateTime = c.getTime();
        return dateTime;
    }
    /**
     * 当前当天起始时间 即：00:00:00
     * @return
     */
    public static Date getTodayStartTime(Date date){

        // 获取当天0点时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    /**
     * 获取当天结束时间 即：23:59:59
     * @return
     */
    public static Date getTodayEndTime(Date date){

        // 获取当天23点时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String pattern)
            throws ParseException
    {
        return Utils.isEmptyString(strDate) ? null : new SimpleDateFormat(
                pattern).parse(strDate);
    }

}
