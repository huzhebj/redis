package com.hualala.redis.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/2.
 */
public class DateUtil {

    public final static String DATE_PATTERN = "yyyyMMdd";
    public final static String DATE_TIME_PATTERN = "yyyyMMddHHmmss";
    public final static String DATE_MILL_PATTERN = "yyyyMMddHHmmssSSS";
    public final static String DATE_MINUTE_PATTERN = "yyyyMMddHHmm";
    public final static String DATE_HOUR_PATTERN = "yyyyMMddHH";

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static String getTimeStamp() {
        Date createStamp = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formater.format(createStamp);
    }

    public static String stringToLong(String stime) {
        if (stime == null) {
            return null;
        } else if (stime.length() >= 10) {
            return (stime.substring(0, 4) + stime.substring(5, 7) + stime.substring(8, 10));
        } else if (stime.length() == 8) {
            return stime;
        } else {
            return "0";
        }

    }

    public static Long getDateChangDay(Long stime, int dayCount) {

        Date startTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            startTime = sdf.parse(stime.toString());

        } catch (Exception e) {
            throw new RuntimeException("String to Date error");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.DAY_OF_MONTH, dayCount);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        String monthStr = month < 10 ? "0" + month : "" + month;
        String dayStr = day < 10 ? "0" + day : "" + day;
        String end = year + "" + monthStr + "" + dayStr;

        return Long.parseLong(end);
    }

    public static int compare(long sTime, long eTime, long startT, long endT) {
        //不包含
        if (eTime < startT || sTime > endT) {
            return 0;
        }
        //内包含
        if ((sTime > startT && eTime <= endT) || (sTime >= startT && eTime < endT)
                || (sTime == startT && eTime < endT) || (eTime == endT && sTime > startT)) {
            return 1;
        }
        //左包含
        if (sTime <= startT && eTime >= startT && eTime < endT) {
            return 2;
        }

        //右包含
        if (sTime >= startT && sTime <= endT && eTime > endT) {
            return 3;
        }
        // 全包含
        if (sTime <= startT && eTime >= endT) {
            return 4;
        }
        return -1;
    }

    /**
     * 获取当前系统日期
     *
     * @return
     */
    public static String getCurrentDate() {
        String date = new SimpleDateFormat(DATE_PATTERN).format(new Date());
        return date;
    }

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        String date = formatDateTime(new Date());
        return date;
    }

    /**
     * 获取当前系统时,毫秒级
     *
     * @return
     */
    public static String getCurrentDateTimeMill() {
        return new SimpleDateFormat(DATE_MILL_PATTERN).format(new Date());
    }

    /**
     * 获取当前系统时,毫秒级
     *
     * @return
     */
    public static String getCurrentDateMinute() {
        return new SimpleDateFormat(DATE_MINUTE_PATTERN).format(new Date());
    }

    public static String getCurrentDateHour() {
        return new SimpleDateFormat(DATE_HOUR_PATTERN).format(new Date());
    }

    /**
     * 获取指定格式的系统日期
     */
    public static String getCurrentDate(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }

    /**
     * 格式化数据
     *
     * @param sourcePattern
     * @param targetPattern
     * @param dateStr
     * @return
     */
    public static String formatDate(String sourcePattern, String targetPattern, String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat(sourcePattern);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            return dateStr;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(targetPattern);
        return sdf.format(date);
    }

    public static String formatDate(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat(DATE_PATTERN).format(date);
    }

    public static String formatDate(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static String formatDateMinute(Date date) {
        return new SimpleDateFormat(DATE_MINUTE_PATTERN).format(date);
    }

    public static String formatDateHour(Date date) {
        return new SimpleDateFormat(DATE_HOUR_PATTERN).format(date);
    }

    public static String formatDateTime(Date date) {
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
    }

    public static String formatDateTimeMill(Date date) {
        return new SimpleDateFormat(DATE_MILL_PATTERN).format(date);
    }

    public static long getCurrentDateTimeLong() {
        return Long.parseLong(getCurrentDateTime());
    }

    public static long getCurrentDateTimeLong(int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, second);
        return Long.parseLong(formatDateTime(calendar.getTime()));
    }

    public static String getCurrentDateMinuteByMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return formatDateMinute(calendar.getTime());
    }

    public static String getCurrentDateMinuteByDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return formatDateMinute(calendar.getTime());
    }

    public static String getCurrentDateMinuteByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hour);
        return formatDateMinute(calendar.getTime());
    }

    public static String getCurrentDateMinuteByMin(int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, min);
        return formatDateMinute(calendar.getTime());
    }

    public static String getCurrentDateMinuteBySec(int sec) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, sec);
        return formatDateMinute(calendar.getTime());
    }

    public static String getCurrentDateHourByMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return formatDateMinute(calendar.getTime());
    }

    public static String getCurrentDateHourByDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return formatDateMinute(calendar.getTime());
    }

    public static String getCurrentDateHourByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hour);
        return formatDateMinute(calendar.getTime());
    }

    public static String getCurrentDateHourByMin(int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, min);
        return formatDateMinute(calendar.getTime());
    }

    public static String getCurrentDateHourBySec(int sec) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, sec);
        return formatDateMinute(calendar.getTime());
    }

    public static String getCurrentDateTimeByMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return formatDateTime(calendar.getTime());
    }

    public static String getCurrentDateTimeByDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return formatDateTime(calendar.getTime());
    }

    public static String getCurrentDateTimeByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hour);
        return formatDateTime(calendar.getTime());
    }

    public static String getCurrentDateTimeByMin(int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, min);
        return formatDateTime(calendar.getTime());
    }

    public static String getCurrentDateTimeBySec(int sec) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, sec);
        return formatDateTime(calendar.getTime());
    }

    public static String getCurrentDateTimeMillByMonth(int month) {
        return getDateTimeByMonth(month, DATE_MILL_PATTERN);
    }

    public static String getCurrentDateTimeMillByDay(int day) {
        return getDateTimeByDay(day, DATE_MILL_PATTERN);
    }

    public static String getCurrentDateTimeMillByHour(int hour) {
        return getDateTimeByHour(hour, DATE_MILL_PATTERN);
    }

    public static String getCurrentDateTimeMillByMin(int min) {
        return getDateTimeByMin(min, DATE_MILL_PATTERN);
    }

    public static String getCurrentDateTimeMillBySec(int sec) {
        return getDateTimeBySec(sec, DATE_MILL_PATTERN);
    }

    public static String getCurrentDateByMonth(int month) {
        return getDateTimeByMonth(month, DATE_PATTERN);
    }

    public static String getCurrentDateByDay(int day) {
        return getDateTimeByDay(day, DATE_PATTERN);
    }

    public static String getCurrentDateByHour(int hour) {
        return getDateTimeByHour(hour, DATE_PATTERN);
    }

    public static String getCurrentDateByMin(int min) {
        return getDateTimeByMin(min, DATE_PATTERN);
    }

    public static String getCurrentDateBySec(int sec) {
        return getDateTimeBySec(sec, DATE_PATTERN);
    }

    public static String getDateTimeByMonth(int month, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    public static String getDateTimeByDay(int day, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    public static String getDateTimeByHour(int hour, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hour);
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    public static String getDateTimeByMin(int min, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, min);
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    public static String getDateTimeBySec(int sec, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, sec);
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    public static long getDateTimeLongByMonth(int month, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return formatDateTimeLong(calendar.getTime(), pattern);
    }

    public static long getDateTimeLongByDay(int day, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return formatDateTimeLong(calendar.getTime(), pattern);
    }

    public static long getDateTimeLongByHour(int hour, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hour);
        return formatDateTimeLong(calendar.getTime(), pattern);
    }

    public static long getDateTimeLongByMin(int min, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, min);
        return formatDateTimeLong(calendar.getTime(), pattern);
    }

    public static long getDateTimeLongBySec(int sec, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, sec);
        return formatDateTimeLong(calendar.getTime(), pattern);
    }

    public static Long formatDateTimeLong(Date date, String pattern) {
        String dateTime = new SimpleDateFormat(pattern).format(date);
        return Long.parseLong(dateTime);
    }

    /**
     * 日期相差秒数(date2-date1)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int diffDateForSec(String date1, String date2) {
        return diffDateForSec(parseDate(date1), parseDate(date2));
    }

    public static int diffDateForSec(Date date1, Date date2) {
        return (int) (date2.getTime() - date1.getTime()) / 1000;
    }

    /**
     * 和当前系统时间相差秒数（date-current)
     *
     * @param date
     * @return
     */
    public static int diffDateForSec(String date) {
        return diffDateForSec(Calendar.getInstance().getTime(), parseDate(date));
    }

    public static Date parseDate(String date) {
        if (date == null) {
            return Calendar.getInstance().getTime();
        }
        SimpleDateFormat df = null;
        if (date.length() == 8) {
            df = new SimpleDateFormat(DATE_PATTERN);
        } else if (date.length() == 12) {
            df = new SimpleDateFormat(DATE_MINUTE_PATTERN);
        } else if (date.length() == 14) {
            df = new SimpleDateFormat(DATE_TIME_PATTERN);
        } else if (date.length() == 17) {
            df = new SimpleDateFormat(DATE_MILL_PATTERN);
        }
        if (df == null) {
            return Calendar.getInstance().getTime();
        }
        try {
            return df.parse(date);
        } catch (Exception e) {
            logger.error("parse date [" + date + "] error", e);
            return Calendar.getInstance().getTime();
        }
    }

    public static Date parseDateTime(String date) {
        return parseDate(date, DATE_TIME_PATTERN);
    }

    public static Date parseDateEndDay(String date) {
        return parseDate(date, DATE_PATTERN);
    }

    public static Date parseDateEndMinute(String date) {
        return parseDate(date, DATE_MINUTE_PATTERN);
    }

    public static Date parseDateEndHour(String date) {
        return parseDate(date, DATE_HOUR_PATTERN);
    }

    public static Date parseDateEndSec(String date) {
        return parseDate(date, DATE_TIME_PATTERN);
    }

    public static Date parseDateEndMill(String date) {
        return parseDate(date, DATE_MILL_PATTERN);
    }

    public static Date parseDate(String date, String pattern) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        String patt = pattern;
        if (StringUtils.isEmpty(patt)) {
            patt = DATE_TIME_PATTERN;
        }
        SimpleDateFormat df = new SimpleDateFormat(patt);
        try {
            return df.parse(date);
        } catch (Exception e) {
            logger.error("parse date [" + date + "] pattern [" + pattern + "] error", e);
            return null;
        }
    }

    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date getDateBySec(int sec) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, sec);
        return calendar.getTime();
    }

    public static Date getDateByMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static Date getDateByDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static Date getDateByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    public static Date getDateByMin(int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, min);
        return calendar.getTime();
    }

    public static String getMaxDate() {
        return "99991231";
    }

    public static String getMaxDateTime() {
        return "99991231235959";
    }

    public static String getMaxDateHour() {
        return "999912312359";
    }

    public static String getMinDate() {
        return "19000101";
    }

    public static String getMinDateTime() {
        return "19000101000000";
    }

    public static String getMinDateHour() {
        return "190001010000";
    }

    public static String calDateTimeByUnit(String dateTime, int num, int unit, String orignPattern, String targetPattern) {
        Date date = parseDate(dateTime, orignPattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit, num);
        return new SimpleDateFormat(targetPattern).format(calendar.getTime());
    }

    public static String calDateTimeByUnit(String dateTime, int num, int unit, String targetPattern) {
        Date date = parseDate(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit, num);
        return new SimpleDateFormat(targetPattern).format(calendar.getTime());
    }

    public static String calDateTimeByMonth(String dateTime, int month, String orignPattern, String targetPattern) {
        return calDateTimeByUnit(dateTime, month, Calendar.MONTH, orignPattern, targetPattern);
    }

    public static String calDateTimeByDay(String dateTime, int day, String orignPattern, String targetPattern) {
        return calDateTimeByUnit(dateTime, day, Calendar.DATE, orignPattern, targetPattern);
    }

    public static String calDateTimeByHour(String dateTime, int hour, String orignPattern, String targetPattern) {
        return calDateTimeByUnit(dateTime, hour, Calendar.HOUR, orignPattern, targetPattern);
    }

    public static String calDateTimeByMin(String dateTime, int min, String orignPattern, String targetPattern) {
        return calDateTimeByUnit(dateTime, min, Calendar.MINUTE, orignPattern, targetPattern);
    }

    public static String calDateTimeBySec(String dateTime, int sec, String orignPattern, String targetPattern) {
        return calDateTimeByUnit(dateTime, sec, Calendar.SECOND, orignPattern, targetPattern);
    }

    public static String calDateTimeByMonth(String dateTime, int month, String orignPattern) {
        return calDateTimeByUnit(dateTime, month, Calendar.MONTH, orignPattern, DATE_TIME_PATTERN);
    }

    public static String calDateTimeByDay(String dateTime, int day, String orignPattern) {
        return calDateTimeByUnit(dateTime, day, Calendar.DATE, orignPattern, DATE_TIME_PATTERN);
    }

    public static String calDateTimeByHour(String dateTime, int hour, String orignPattern) {
        return calDateTimeByUnit(dateTime, hour, Calendar.HOUR, orignPattern, DATE_TIME_PATTERN);
    }

    public static String calDateTimeByMin(String dateTime, int min, String orignPattern) {
        return calDateTimeByUnit(dateTime, min, Calendar.MINUTE, orignPattern, DATE_TIME_PATTERN);
    }

    public static String calDateTimeBySec(String dateTime, int sec, String orignPattern) {
        return calDateTimeByUnit(dateTime, sec, Calendar.SECOND, orignPattern, DATE_TIME_PATTERN);
    }

    public static String calDateTimeByMonth(String dateTime, int month) {
        return calDateTimeByUnit(dateTime, month, Calendar.MONTH, DATE_TIME_PATTERN);
    }

    public static String calDateTimeByDay(String dateTime, int day) {
        return calDateTimeByUnit(dateTime, day, Calendar.DATE, DATE_TIME_PATTERN);
    }

    public static String calDateTimeByHour(String dateTime, int hour) {
        return calDateTimeByUnit(dateTime, hour, Calendar.HOUR, DATE_TIME_PATTERN);
    }

    public static String calDateTimeByMin(String dateTime, int min) {
        return calDateTimeByUnit(dateTime, min, Calendar.MINUTE, DATE_TIME_PATTERN);
    }

    public static String calDateTimeBySec(String dateTime, int sec) {
        return calDateTimeByUnit(dateTime, sec, Calendar.SECOND, DATE_TIME_PATTERN);
    }

    public static long getCurrentDateTimeMillLong() {
        return Long.valueOf(getCurrentDateTimeMill());
    }

    public static long getCurrentTimeInMillis() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }

    public static long getCurrentTimeInMillis(String dateStr, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate(dateStr, pattern));
        return calendar.getTimeInMillis();
    }

}
