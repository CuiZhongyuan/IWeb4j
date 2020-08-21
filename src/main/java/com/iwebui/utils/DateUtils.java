package com.iwebui.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * 日期工具类
 *
 * @author czy
 * @date 2020年08月03日上午午11:20:58
 */
public class DateUtils extends DateUtil {
    /**
     * 例：2019
     */
    public final static String YYYY = "yyyy";
    /**
     * 例：2019-10
     */
    public final static String YYYY_MM = "yyyy-MM";
    /**
     * 例：2019-10-01
     */
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 例：10-10 10:10:10
     */
    public final static String MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";
    /**
     * 例：10:10
     */
    public final static String HH_MM = "HH:mm";
    /**
     * 例：2019-10-10 10:10:10
     */
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 例：10-10
     */
    public final static String MM_DD = "MM-dd";
    /**
     * 例：10:10:10
     */
    public final static String HH_MM_SS = "HH:mm:ss";

    /**
     * 返回YYYY 为格式的时间字符
     *
     * @return String
     */
    public static String getCurrentYear() {
        return DateTime.now().toString(YYYY);
    }

    /**
     * 返回YYYY-MM 为格式的时间字符
     *
     * @return String
     */
    public static String getCurrentYearAndMonth() {
        return DateTime.now().toString(YYYY_MM);
    }

    /**
     * 返回YYYY-MM-DD 为格式的时间字符
     *
     * @return String
     */
    public static String getCurrentYearAndMonthAndDate() {
        return DateTime.now().toString(YYYY_MM_DD);
    }

    /**
     * 返回YYYY-MM-DD HH:mm:ss 为格式的时间字符
     *
     * @return String
     */
    public static String getCurrentTime() {
        return DateTime.now().toString(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 返回MM-dd HH:mm:ss 为格式的时间字符
     *
     * @return String
     */
    public static String getCurrentDateAndTime() {
        return DateTime.now().toString(MM_DD_HH_MM_SS);
    }


    /**
     * 返回HH:mm 为格式的时间字符
     *
     * @return String
     */
    public static String getCurrentHourAndMinutes() {
        return DateTime.now().toString(HH_MM);
    }

    /**
     * 返回HH:mm:ss 为格式的时间字符
     *
     * @return String
     */
    public static String getCurrentHourAndMinutesAndSeconds() {
        return DateTime.now().toString(HH_MM_SS);
    }

    /**
     * 返回YYYYMMDDHHmmss 为格式的时间字符
     *
     * @return String
     */
    public static String getTimeMillis() {
        return DateTime.now().toString(DatePattern.PURE_DATETIME_MS_PATTERN);
    }

    /**
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        return new DateTime(date).toString(format);
    }

    /**
     * 返回当前年
     *
     * @return int
     */
    public static int getYear() {
        return thisYear();
    }

    /**
     * 返回当前月份
     *
     * @return int
     */
    public static int getMonth() {
        return thisMonth();
    }

    /**
     * 返回当天是当月的第几号
     *
     * @return int
     */
    public static int getDay() {
        return thisDayOfMonth();
    }

    /**
     * 返回当前小时,24小时制
     *
     * @return String
     */
    public static int getHour() {
        return thisHour(true);
    }

    /**
     * 返回当前毫秒数
     *
     * @return String
     */
    public static long getCurrentMillisecond() {
        return System.currentTimeMillis();
    }


    /**
     * 获取传入时间之后的多少天
     *
     * @param date 传入时间
     * @param i    加几天
     * @return 返回时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getAfterDay(Date date, int i) {
        return offsetDay(date, i).toString();
    }

    /**
     * 获取几天前的时间 时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param beforeDay
     * @return 返回时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getBeforeDay(Date date, int beforeDay) {
        return offsetDay(date, -beforeDay).toString();
    }

    /**
     * 获取传入时间之后多少给小时的时间
     *
     * @param date 传入时间
     * @param i    加几个小时
     * @return 返回时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getAfterHour(Date date, int i) {
        return offsetHour(date, i).toString();
    }

    /**
     * 获取传入时间之前多少给小时的时间 时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param beforeHour
     * @return 返回时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getBeforeHour(Date date, int beforeHour) {
        return offsetDay(date, -beforeHour).toString();
    }

    /**
     * 把日期字符串转换为具体日期刊
     *
     * @param dateString
     * @param format     可以是yyyy-MM-dd HH:mm:ss，yyyyMMddHHmmss，yyyy-MM-dd任何需要得到的模式
     * @return
     */
    public static Date stringToDate(String dateString, String format) {
        return parse(dateString, format);
    }

    /**
     * 获取某年最后一天日期,返回时间格式--例:"2019-12-31 23:59:59"
     *
     * @param date
     * @return 返回时间格式--例:"2019-12-31 23:59:59"
     */
    public static String getYearLastDay(Date date) {
        return endOfYear(date).toString();
    }

    /**
     * 相差时间天数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long getBetweenDay(String time1, String time2) {
        Date date1 = parse(time1);
        Date date2 = parse(time2);
        return between(date1, date2, DateUnit.DAY);
    }

    /**
     * 相差时间秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long getBetweenSecond(String time1, String time2) {
        Date date1 = parse(time1);
        Date date2 = parse(time2);
        return between(date1, date2, DateUnit.SECOND);
    }

    /**
     * 毫秒数转换为"yyyy-MM-dd HH:mm:ss"格式时间
     *
     * @return String
     */
    public static String getTimeForMillisecond(Long millisecond) {
        return date(calendar(millisecond)).toString();
    }

    /**
     * 判断 当前时间在开始时间与结束时间之间
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param judgeTime 需要判断的时间
     * @return
     */
    public static Boolean betweenIsStartAndEnd(String startTime, String endTime, String judgeTime) {
        return isIn(parse(judgeTime), parse(startTime), parse(endTime));
    }

    /**
     * 判断 时间1是否大于时间2
     *
     * @return 计算错误返回0
     */
    public static Boolean compareDateTime(String time1, String time2, String format) {
        Date date1 = stringToDate(time1, format);
        Date date2 = stringToDate(time2, format);
        if (date1.getTime() > date2.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算出开始时间和结束时间的毫秒差，判断是否超过设定的超时时间，返回描述时间和描述信息字符串
     *
     * @param overrideTime 设定的超时时间
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @return
     */
    public static String logTimeout(long overrideTime, long startTime, long endTime) {
        long s = endTime - startTime;
        return s > overrideTime ? s + "ms,超时" : s + "ms";
    }

}

