package com.smart.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by syl on 2016/5/4.
 */
public class DateUtil {

    public static final int FILTER_SUNDAY = 1;
    public static final int FILTER_MONDAY = 2;
    public static final int FILTER_TUESDAY = 3;
    public static final int FILTER_WEDNESDAY = 4;
    public static final int FILTER_THURSDAY = 5;
    public static final int FILTER_FRIDAY = 6;
    public static final int FILTER_SATURDAY = 7;

    //private static SimpleDateFormat dateFormat =
    //        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //private static SimpleDateFormat dateFormatHHmm =
    //        new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //private static SimpleDateFormat dateFormatS =
    //        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    public static String DATE_FORMAT_NOSPLIT = "yyyyMMddHHmmssSSS";

    /**
     * 将Date类型转换为字符串
     *
     * @param date
     *            日期类型
     * @return 日期字符串
     */
    public static String format(Date date) {
        if(date == null){
            return "";
        }
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(Calendar date) {
        if(date == null){
            return "";
        }
        return format(date.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将Date类型转换为字符串
     *
     * @param date
     *            日期类型
     * @param pattern
     *            字符串格式
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "null";
        }
        if (pattern == null || pattern.equals("") || pattern.equals("null")) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将字符串转换为Date类型
     *
     * @param date
     *            字符串类型
     * @return 日期类型
     */
    public static Date format(String date) {
        return format(date, null);
    }

    /**
     * 将字符串转换为Date类型
     *
     * @param date
     *            字符串类型
     * @param pattern
     *            格式
     * @return 日期类型
     */
    public static Date format(String date, String pattern) {
        if (pattern == null || pattern.equals("") || pattern.equals("null")) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (date == null || date.equals("") || date.equals("null")) {
            return new Date();
        }
        Date d = null;
        try {
            d = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException pe) {
        }
        return d;
    }

    public static String getCurrDate() {
        return format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 格局传入的日期 解析出 该用何种SimpleDateFormat
     * @param dateStr
     * @return
     */
    public static SimpleDateFormat  getSimpleDateFormatPattern(String dateStr) {
        SimpleDateFormat format = null;
        if (Pattern.matches("\\d{4}-\\d{1,2}-\\d{1,2}", dateStr)) {
            format = new SimpleDateFormat("yyyy-MM-dd");
            //System.out.println("用的yyyy-MM-dd");
        }else if (Pattern.matches("\\d{4}\\d{2}\\d{2}", dateStr)) {
            format = new SimpleDateFormat("yyyyMMdd");
        }else if (Pattern.matches("\\d{4}年\\d{2}月\\d{2}日", dateStr)) {
            format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
            // System.out.println("用的yyyy年MM月dd日");
        }else if (Pattern.matches("\\d{4}年\\d{1,2}月\\d{1,2}日", dateStr)) {
            format = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        }else if (Pattern.matches("\\d{1,2}\\w{3}\\d{4}", dateStr)) {
            format = new SimpleDateFormat("dMMMyyyy", Locale.ENGLISH);
        }else if (Pattern.matches("\\d{1,2}-\\w{3}-\\d{4}", dateStr)) {
            format = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH);
        }else if(Pattern.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}", dateStr)) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
        else if (dateStr.length() > 20 ) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }else {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return format;
    }


    /**
     * 格局传入的日期 解析出 该用何种SimpleDateFormat
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseDateByPattern(String dateStr){
        SimpleDateFormat format = null;
        try{
            if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", dateStr)) {
                format = new SimpleDateFormat("yyyy-MM-dd");
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}/\\d{2}/\\d{2}", dateStr)) {
                format = new SimpleDateFormat("yyyy/MM/dd");
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}\\d{2}\\d{2}", dateStr)) {
                format = new SimpleDateFormat("yyyyMMdd");
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}年\\d{2}月\\d{2}日", dateStr)) {
                format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}年\\d{1,2}月\\d{1,2}日", dateStr)) {
                format = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}/\\d{1,2}/\\d{1,2} \\d{2}:\\d{2}", dateStr)) {
                format = new SimpleDateFormat("yyyy/M/d HH:mm", Locale.CHINA);
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}\\.\\d{2}\\.\\d{2}", dateStr)) {
                format = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}年\\d{2}月", dateStr)) {
                format = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", dateStr)) {
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}", dateStr)) {
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}", dateStr)) {
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{2}", dateStr)) {
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS", Locale.CHINA);
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{1}", dateStr)) {
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.CHINA);
                return format.parse(dateStr);
            }else if (Pattern.matches("\\d{2}:\\d{2}", dateStr)) {
                format = new SimpleDateFormat("HH:mm", Locale.CHINA);
                return format.parse(dateStr);
            }
        }catch(Exception e){

        }
        return null;
    }

    /**
     * 获取时间日期
     * @param milliSeconds
     * @return
     */
    public static String getTimeMilisecondDesc(long milliSeconds){
        long days = milliSeconds/(1000*60*60*24);
        milliSeconds = milliSeconds-(days*24*60*60*1000);
        long hours = milliSeconds/(1000*60*60);
        milliSeconds = milliSeconds-(hours*60*60*1000);
        long minutes = milliSeconds/(1000*60);
        milliSeconds = milliSeconds-(minutes*60*1000);
        long seconds = milliSeconds/(1000);

        StringBuffer sb = new StringBuffer();
        if(days!=0){
            sb.append(days+"天");
        }
        if(hours!=0){
            sb.append(hours+"小时");
        }
        if(minutes!=0){
            sb.append(minutes+"分钟");
        }
        if(seconds!=0){
            sb.append(seconds+"秒");
        }
        return sb.toString();
    }

    /**
     * 取得当前日期所在周的第一天

     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = clearDate(date, 4);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }
    /**
     * 取得当前日期所在周的最后一天

     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = clearDate(date, 4);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 000);
        return c.getTime();
    }

    /**
     * 取得指定日期的当周的起始时间
     * @param date
     * @return
     */
    public static Date[] getWeekLimit(Date date) throws Exception {
        Date date1 = getFirstDayOfWeek(date);
        Date date2 = getLastDayOfWeek(date);
        return new Date[]{date1, date2};
    }

    /**
     * 取得指定日期的当月起始时间

     * @param date
     * @return
     */
    public static Date[] getMonthLimit(Date date) throws Exception {
        Calendar cal = clearDate(date, 5);
        cal.set(Calendar.DATE, 1);
        Date date1 = cal.getTime();

        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.SECOND, -1);
        Date date2 = cal.getTime();

        return new Date[]{date1, date2};
    }

    /**
     * 取得指定日期的当年起始时间

     * @param date
     * @return
     */
    public static Date[] getYearLimit(Date date) throws Exception {
        Calendar cal = clearDate(date, 6);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        Date date1 = cal.getTime();

        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.SECOND, -1);
        Date date2 = cal.getTime();

        return new Date[]{date1, date2};
    }
    /**
     * 取得指定日期当月的起始时间串
     * @param date
     * @return
     */
    public static String[] getMonthLimitStr(Date date) throws Exception {
        Date[] rtDateArray = getMonthLimit(date);
        return new String[]{getDateTimeStr(rtDateArray[0]), getDateTimeStr(rtDateArray[1])};
    }
    /**
     * 取得指定日期当年的起始时间串
     * @param date
     * @return
     */
    public static String[] getYearLimitStr(Date date) throws Exception {
        Date[] rtDateArray = getYearLimit(date);
        return new String[]{getDateTimeStr(rtDateArray[0]), getDateTimeStr(rtDateArray[1])};
    }

    /**
     * 取得后一天的时间字

     * @param dateStr
     * @return
     */
    public static Date getDayAfter(String dateStr, int dayCnt) throws Exception {
        return getDayAfter(parseDate(dateStr), dayCnt);
    }

    /**
     * 取得后一天的时间
     * @param date
     * @return
     */
    public static Date getDayAfter(Date date, int dayCnt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, dayCnt);
        return cal.getTime();
    }

    /**
     * 设置前多少秒的时间
     * @param date
     * @return
     */
    public static Date getSecondAfter(Date date, int secondCnt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.SECOND, secondCnt);
        return cal.getTime();
    }


    /**
     * 取得后多少小时的时间
     * @param date
     * @return
     */
    public static Date getDayHourAfter(Date date, int hourCnt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hourCnt);
        return cal.getTime();
    }

    /**
     * 取得后多少月的时间
     * @param date
     * @param monthCnt
     * @return
     */
    public static Date getMonthAfter(Date date , int monthCnt){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthCnt);
        return cal.getTime();
    }

    /**
     * 取得后多少年的时间
     * @param date
     * @return
     */
    public static Date getYearAfter(Date date , int yearCnt){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.YEAR, yearCnt);
        return cal.getTime();
    }


    /**
     * 取得后N天的时间的所有日期list
     * @param date
     * @return
     */
    public static List<Date> getDayListAfter(Date date, int dayCnt) {
        List<Date> list = new ArrayList<Date>();
        GregorianCalendar cal = new GregorianCalendar();
        for (int i = 1; i <=dayCnt; i++) {
            cal.setTime(date);
            cal.add(Calendar.DATE, i);
            list.add(cal.getTime());
        }
        return list;
    }

    /**
     * 取得指定天数差的时间字

     * @param dateStr
     * @return
     */
    public static Date getDayDiff(String dateStr, int dayCnt) throws Exception {
        return getDayDiff(parseDate(dateStr), dayCnt);
    }

    /**
     * 取得指定天数差的时间
     * @param date
     * @return
     */
    public static Date getDayDiff(Date date, int dayCnt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, dayCnt);
        return cal.getTime();
    }

    /**
     * 取得前一天的时间字

     * @param dateStr
     * @return
     */
    public static Date getYestday(String dateStr) throws Exception {
        return getYestday(parseDate(dateStr));
    }

    /**
     * 取得前一天的时间
     * @param date
     * @return
     */
    public static Date getYestday(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 取得前一天的时间字符串

     * @param dateStr
     * @return
     */
    public static String getYestdayStr(String dateStr) throws Exception {
        return getYestdayStr(parseDate(dateStr));
    }

    /**
     * 取得前一天的时间字符串

     * @param date
     * @return
     */
    public static String getYestdayStr(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return getDateTimeStr(cal.getTime());
    }


    /**
     * Date清零
     * @param date
     * @param clearNum 1=毫秒, 2=秒, 3=分钟, 4=小时, 5=天, 6=月份
     * @return
     */
    public static Calendar clearDate(Date date, int clearNum) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        //毫秒
        if (clearNum > 0) {
            cal.set(Calendar.MILLISECOND, 0);
        }
        //秒

        if (clearNum > 1) {
            cal.set(Calendar.SECOND, 0);
        }
        //分钟
        if (clearNum > 2) {
            cal.set(Calendar.MINUTE, 0);
        }
        //小时
        if (clearNum > 3) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
        }
        //天

        if (clearNum > 4) {
            cal.set(Calendar.DATE, 0);
        }
        //月份
        if (clearNum > 5) {
            cal.set(Calendar.MONTH, 0);
        }
        return cal;
    }



    /**
     * 把字符串转化为Date
     * @param dateStr
     * @return
     */
    public static Date parseDate(String formatStr, String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.parse(dateStr);
    }


    /*
     * @param dateL
     * @return
     * @throws ParseException
     */
    public static Date parseDate(Long dateL) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(dateL);
        return c.getTime();
    }
    public static Calendar parseCalendar(String formatStr, String dateStr){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(parseDate(formatStr, dateStr));
        } catch (Exception e) {
            // TODO Auto-generated catch block
//				e.printStackTrace();
            return null;
        }
        return c;
    }

    public static Calendar parseCalendar(String dateStr){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(parseDateByPattern(dateStr));
        } catch (Exception e) {
            // TODO Auto-generated catch block
//				e.printStackTrace();
            return null;
        }
        return c;
    }

    /**
     * 把字符串转化为Date
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }

        SimpleDateFormat format = null;
        if (Pattern.matches("\\d{4}-\\d{1,2}-\\d{1,2}", dateStr)) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        }else if (Pattern.matches("\\d{4}\\d{2}\\d{2}", dateStr)) {
            format = new SimpleDateFormat("yyyyMMdd");
        }else if (Pattern.matches("\\d{4}年\\d{2}月\\d{2}日", dateStr)) {
            format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        }else if (Pattern.matches("\\d{4}年\\d{1,2}月\\d{1,2}日", dateStr)) {
            format = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        }else if (Pattern.matches("\\d{1,2}\\w{3}\\d{4}", dateStr)) {
            format = new SimpleDateFormat("dMMMyyyy", Locale.ENGLISH);
        }else if (Pattern.matches("\\d{1,2}-\\w{3}-\\d{4}", dateStr)) {
            format = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH);
        }else if (dateStr.length() > 20 ) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        }else {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 取得的时间串，格式为 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateTimeStr(Date date) {
        if (date == null) {
            return getCurDateTimeStr();
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }


    /**
     * 取得当前的时间，格式为 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurDateTimeStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    /**
     * @return计算一年的最大周
     * @author syl
     * @date 2014-1-11
     * @param year
     * @return
     */
    public static int getMaxWeekOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekOfYear(c.getTime());
    }
    /**
     * 获取日期当前周
     * @author syl
     * @date 2014-1-11
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     *  参数说明  返回一个Calendar数组，长度为2
     * 分别是开始日期和结束日期
     * @author syl
     * @date 2014-1-11
     * @param year 年分 例如 2014 i
     * @param weeknum 第几周 例如33
     * @return
     */
    public static Calendar[] getStartAndEndDate(int year, int weeknum) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weeknum);
        int nw = cal.get(Calendar.DAY_OF_WEEK);
        Calendar start = (Calendar) cal.clone();
        Calendar end = (Calendar) cal.clone();
        start.add(Calendar.DATE, 1 - nw + 1);
        end.add(Calendar.DATE, 7 - nw + 1);
        Calendar[] darr = { start, end };
        return darr;
    }

    /**
     * 获取当期日期的一周 开始可结束日期 返回一个Calendar数组，长度为2  分别是开始日期和结束日期
     * @author syl
     * @date 2014-1-11
     * @return
     */
    public static Calendar[] getCurrStartEndDate() {
        Calendar cal = Calendar.getInstance();
        // 向后推一天（从星期一到周末）
        cal.add(Calendar.DATE, -1);
        int nw = cal.get(Calendar.DAY_OF_WEEK);
        Calendar start = (Calendar) cal.clone();
        Calendar end = (Calendar) cal.clone();
        start.add(Calendar.DATE, 1 - nw + 1);
        end.add(Calendar.DATE, 7 - nw + 1);
        Calendar[] darr = { start, end };
        return darr;
    }

    /**
     * 获取当期日期的一周 开始至结束日期 返回一个Calendar数组，长度为7
     * @author syl
     * @date 2014-1-11
     * @return
     */
    public static Calendar[] getCurrStartAndEndOfWeek() {
        Calendar cal = Calendar.getInstance();
        // 向后推一天（从星期一到周末）
        cal.add(Calendar.DATE, -1);
        int nw = cal.get(Calendar.DAY_OF_WEEK);
        Calendar start = (Calendar) cal.clone();
        start.add(Calendar.DATE, 1 - nw + 1);
        Calendar tuesday = (Calendar) cal.clone();//周二
        tuesday.add(Calendar.DATE, 2 - nw + 1);
        Calendar wednesday = (Calendar) cal.clone();//周三
        wednesday.add(Calendar.DATE, 3 - nw + 1);
        Calendar thursday = (Calendar) cal.clone();//周四
        thursday.add(Calendar.DATE, 4- nw + 1);
        Calendar friday = (Calendar) cal.clone();//周五
        friday.add(Calendar.DATE, 5 - nw + 1);
        Calendar saturday = (Calendar) cal.clone();//周六
        saturday.add(Calendar.DATE, 6 - nw + 1);
        Calendar end = (Calendar) cal.clone();
        end.add(Calendar.DATE, 7 - nw + 1);
        Calendar[] darr = { start, tuesday, wednesday , thursday , friday , saturday , end };
        return darr;
    }


    /**
     * 获取当期日期的一周 开始至结束日期 返回一个Calendar数组，长度为7
     * @author syl
     * @date 2014-1-11
     * @return
     */
    public static Calendar[] getStartAndEndOfWeekByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 向后推一天（从星期一到周末）
        cal.add(Calendar.DATE, -1);
        int nw = cal.get(Calendar.DAY_OF_WEEK);
        Calendar start = (Calendar) cal.clone();
        start.add(Calendar.DATE, 1 - nw + 1);
        Calendar tuesday = (Calendar) cal.clone();//周二
        tuesday.add(Calendar.DATE, 2 - nw + 1);
        Calendar wednesday = (Calendar) cal.clone();//周三
        wednesday.add(Calendar.DATE, 3 - nw + 1);
        Calendar thursday = (Calendar) cal.clone();//周四
        thursday.add(Calendar.DATE, 4 - nw + 1);
        Calendar friday = (Calendar) cal.clone();//周五
        friday.add(Calendar.DATE, 5 - nw + 1);
        Calendar saturday = (Calendar) cal.clone();//周六
        saturday.add(Calendar.DATE, 6 - nw + 1);
        Calendar end = (Calendar) cal.clone();
        end.add(Calendar.DATE, 7 - nw + 1);
        Calendar[] darr = { start, tuesday, wednesday , thursday , friday , saturday , end };
        return darr;
    }

    /**
     * 算出俩个时间，所间隔的多少天
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getDaysBetween(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
    }
    /**
     * 根据指定的月字符串算出月初和月未的时间，精确到秒
     * @param monthStr
     * @return
     */
    public static Date[] getDatetimeMonthLimit(String monthStr){
        String start = monthStr + "-01 00:00:00";
        Date startTime = DateUtil.format(start);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        Date endTime = new Date(cal.getTime().getTime() + 24 * 60 * 60 *1000l - 1l);
        return new Date[]{startTime , endTime};
    }

    /**
     * 上月
     * 根据指定的月字符串算出月初和月未的时间，精确到秒
     * @param monthStr
     * @return
     */
    public static Date[] getDatetimePreMonthLimit(String monthStr){
        String start = monthStr + "-01 00:00:00";
        Date startTime = DateUtil.format(start);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        cal.add(Calendar.MONTH, -1);
        startTime = cal.getTime();

        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        Date endTime = new Date(cal.getTime().getTime() + 24 * 60 * 60 *1000l - 1l);
        return new Date[]{startTime , endTime};
    }
    /**
     * 根据指定的天字符串算出天开始和天结束的时间，精确到秒
     * @return
     */
    public static Date[] getDatetimeDayLimit(String day){
        String startStr = day + " 00:00:00";
        Date start = DateUtil.parseDate(startStr);
        return new Date[]{ start, new Date(start.getTime()+ 24 * 60 * 60 *1000l - 1l)};
    }

    /**
     * 前一天
     * 根据指定的天字符串算出天开始和天结束的时间，精确到秒
     * @return
     */
    public static Date[] getDatetimePreDayLimit(String day){
        String startStr = day + " 00:00:00";
        Date start = new Date(DateUtil.parseDate(startStr).getTime() - 24*60*60*1000l);
        return new Date[]{ start, new Date(start.getTime()+ 24 * 60 * 60 *1000l - 1l)};
    }



    /**
     *
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }


    /**
     * 根据指定的季度 算出季度初和季度未的时间，精确到秒
     *
     * @param year 年份
     * @param nSeason 第几季度
     * @return
     */
    public static Date[] getDatetimeSeasonLimit(int year  , int nSeason){
        Calendar c = Calendar.getInstance();
        Date[] season = new Date[2];
        c.set(year, Calendar.JANUARY, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        if (nSeason == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            c.set(Calendar.DATE,c.getActualMaximum(Calendar.DATE));
            season[1] = new Date(c.getTime().getTime() + 24 * 60 *60 *1000l - 1l) ;
        } else if (nSeason == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            c.set(Calendar.DATE,c.getActualMaximum(Calendar.DATE));
            season[1] = new Date(c.getTime().getTime() + 24 * 60 *60 *1000l- 1l) ;
        } else if (nSeason == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            c.set(Calendar.DATE,c.getActualMaximum(Calendar.DATE));
            season[1] = new Date(c.getTime().getTime() + 24 * 60 *60 *1000l- 1l) ;
        } else if (nSeason == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            c.set(Calendar.DATE,c.getActualMaximum(Calendar.DATE));
            season[1] = new Date(c.getTime().getTime() + 24 * 60 *60 *1000l- 1l) ;
        }

        return season;
    }

    /**
     * 上一季度
     * 根据指定的季度 算出季度初和季度未的时间，精确到秒
     * @param year 年份
     * @param nSeason 第几季度
     * @return
     */
    public static Date[] getDatetimePreSeasonLimit(int year  , int nSeason){
        if (nSeason == 1) {
            nSeason = 4;
            year = year - 1;
        } else  {
            nSeason = nSeason - 1;
        }
        return getDatetimeSeasonLimit(  year  ,   nSeason);
    }

    /**
     * 根据指定的年算出年初和年未的时间，精确到秒
     *
     * @param year 年份
     * @return
     */
    public static Date[] getDatetimeYearLimit(int year){
        Calendar c = Calendar.getInstance();
        Date[] res = new Date[2];
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        res[0] = c.getTime();
        c.set(Calendar.MONTH, Calendar.DECEMBER);
        c.set(Calendar.DATE,c.getActualMaximum(Calendar.DATE));
        res[1] = new Date(c.getTime().getTime() + 24 * 60 *60 *1000l- 1l) ;
        return res;
    }

    /**
     * 去年
     * 根据指定的年算出年初和年未的时间，精确到秒
     * @param year 年份
     * @return
     */
    public static Date[] getDatetimePreYearLimit(int year){
        return getDatetimeYearLimit(year - 1);
    }

    /**
     * 获取加几天后的时间
     * @param date
     * @param number 天数数量
     * @return
     */
    public static Date getNextDay(Date date,int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +number);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }

    /**
     * 计算俩个时间差多少天多少小时
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoor( Date nowDate,Date endDate) {

        long nd = 1000 * 24 * 60 * 60l;
        long nh = 1000 * 60 * 60l;
        long nm = 1000 * 60l;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时";
    }


    /**
     * 计算俩个时间差多少天多少小时分钟
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoor2( Date nowDate,Date endDate) {

        long nd = 1000 * 24 * 60 * 60l;
        long nh = 1000 * 60 * 60l;
        long nm = 1000 * 60l;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        String tempStr = "";
        if(day > 0){
            tempStr = day + "天";
        }
        if(hour > 0){
            tempStr += hour + "小时";
        }
        if(min > 0){
            tempStr += min + "分钟";
        }
        return tempStr;
    }

    public static  String getTimes(String date) throws ParseException {
        String uu="上午";
        if(Integer.valueOf(date.substring(0,2))>12){
            uu="下午";
        }
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        Date dates = null;
        dates = dateFormat.parse(date);
        date= dateFormat.format(dates);
        return uu+(date.substring(0,5));
    }

    /**
     * 重新设置归属日期
     * @param date
     * @param startTime
     * @param endTime
     * @return
     */
    public static Date getReSetBelongDate(Date date , String startTime  , String endTime){
        if(StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)){
            return date;
        }
        if(startTime.compareTo(endTime) > 0){//跨天
            String dateStr = format(date , "yyyy-MM-dd");
            dateStr = dateStr + " " +  endTime;
            //转换成日期
            Calendar calEnd = parseCalendar(dateStr);
            Date start  = calEnd.getTime();
            calEnd.add(Calendar.DAY_OF_MONTH , +1);
            Date end = calEnd.getTime();
            //System.out.println(format(start) +":"+ format(end));
            if(date.compareTo(start) < 0){//如果日期小于营业开始时间
                calEnd.add(Calendar.DAY_OF_MONTH , -2);
                return calEnd.getTime();
            }
            return start;
        }
        return date;
    }

    /**
     * 当前季度的开始时间
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 1);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 当前季度的结束时间
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH,8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    public static String getWeek(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 获取两个日期相隔天数  去掉时分秒，直接比对日
     * @param fDate
     * @param oDate
     * @return
     */
    public static int getIntervalOfDays(Date fDate, Date oDate) {
        if (null == fDate || null == oDate) {
            return -1;
        }
        fDate = DateUtil.clearDate(fDate, 4).getTime();
        oDate = DateUtil.clearDate(oDate, 4).getTime();
        long intervalMilli = oDate.getTime() - fDate.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    public static String getStrTime(String cc_time) {
        BigDecimal bigDecimal = new BigDecimal(cc_time);
        System.out.print(" bigDecimal.longValue()" +  bigDecimal.longValue())  ;
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH时mm分");
        long lcc_time = Long.valueOf(bigDecimal.longValue());
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    public static int getMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getYear(Date date){
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.YEAR);
    }

    public static Date getBeaferTime(Date date){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }


    public static Date getDateBefore(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

    /**
     * 获取上一个月
     * @param date
     * @return
     */
    public static String getBeaferMonth(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }

    /**
     * 获取本月
     * @param date
     * @return
     */
    public static String getNowWeek(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }

    /**
     * 上一周
     * @param date
     * @return
     */
    public static String getBeaferWeek(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }


    /**
     * 获取前几分钟的时间
     * @param date 日期
     * @param minute 前几分钟
     * @return
     */
    public static Date getBeforeMinute(Date date,int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE,-minute);
        return calendar.getTime();
    }

    public static void main(String[] args) {
//        System.out.println(getBeaferWeek(new Date()));
        Date date = new Date();
        System.out.println(DateUtil.getDateTimeStr(date));
        System.out.println(DateUtil.getDateTimeStr(getBeforeMinute(date,20)));
    }

}
