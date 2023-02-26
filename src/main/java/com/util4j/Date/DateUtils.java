package com.util4j.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private static SimpleDateFormat sf = null;


    /**
     * 获取当天日期yyyyMMdd
     * @return
     */
    public static String getStrDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    /**
     * 获取系统时间
     */
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    /**
     * 时间戳转换成字符串
     */
    public static String convertDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /**
     * 时间戳转换成字符串
     */
    public static String convertTimeStampToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    /**
     * 将字符串转为时间戳
     */
    public static long convertStringToTimeStamp(String time) {
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            logger.error("convert string to timestamp fail,error msg is :{}",e.getMessage() );
        }
        return date.getTime();
    }

    /**
     * 直接获取时间戳
     */
    public static String getTimeStamp() {
        String currentDate = getCurrentDate();
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(currentDate);
        } catch (ParseException e) {
            logger.error("get timestamp fail,error msg is :{}",e.getMessage() );
        }
        return String.valueOf(date.getTime());
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //不同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //同年
        {
            return day2-day1;
        }
    }

    public static int differentDaysByMillisecond(Long date1,Long date2)
    {
        int days = (int) ((date2 - date1) / (1000*3600*24));
        return days;
    }


    /**
     * 获取几天前的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {

        Calendar now = Calendar.getInstance();

        now.setTime(d);

        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);

        return now.getTime();

    }

    /**
     * 获取几天后的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);

        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);

        return now.getTime();

    }

    /**
     * 获取自然日 （当天、返回1）
     * @param startTime 开始时间，时间戳（毫秒数）
     * @return 默
     */
    public static Integer getNaturalDays(Long startTime) {
        LocalDateTime openDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault());
        LocalDate openDate = openDateTime.toLocalDate();
        LocalDate today =  LocalDate.now();
        Period between = Period.between(openDate, today);
        return between.getDays() + 1;
    }

}
