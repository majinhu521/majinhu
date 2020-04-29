package com.study.majinhu.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 说明：日期处理
 * 创建人：FH Q313596790
 * 修改时间：2015年11月24日
 * @version
 */
public class DateUtil {

    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
    private final static SimpleDateFormat sdfTTimes = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private static Date time_base = new Date();


    static {
        try {
            time_base = DateUtils.parseDate("2000-01-01", "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getHandleDateTime(String handleTime) {
        Date date = DateUtils.addSeconds(time_base, Integer.parseInt(handleTime, 16));
        return sdfTimes.format(date);
    }

    public static String calHandleDateTime(String agmHandleDate, Map<String, String> ticketMap) {
        String generateDate = ticketMap.get("LOCAL_GEN_TIME");
        int seconds = Integer.parseInt(generateDate, 16);
        int handleSecond = Integer.parseInt(agmHandleDate, 16);
        return DateFormatUtils.format(DateUtils.addSeconds(time_base, handleSecond + seconds), "yyyyMMddHHmmss");
    }

    public static Date getDateTime(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获取YYYY格式
     * @return
     */
    public static String getSdfTimes() {
        return sdfTimes.format(new Date());
    }

    /**
     * 获取YYYY格式
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     * @return
     */
    public static String getDays(){
        return sdfDays.format(new Date());
    }



    /**
     * 获取昨天日期 yyyymmdd
     * @return
     */
    public static String getYesterdays(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        return sdfDays.format(calendar.getTime());
    }

    /**
     * 获取昨天日期 yyyy-mm-dd
     * @return
     */
    public static String getYesterday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        return sdfDay.format(calendar.getTime());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * 获取yyyyMMddHHmmss格式
     * @return
     */
    public static String getTimes() {
        return sdfTimes.format(new Date());
    }

    /**
     * 获取T格式
     * @return
     */
    public static String getSdfTTimes() {
        return sdfTTimes.format(new Date());
    }
    /**
     * @Title: compareDate
     * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @author fh
     */
    public static boolean compareDate(String s, String e) {
        if(fomatDate(s)==null||fomatDate(e)==null){
            return false;
        }
        return fomatDate(s).getTime() >=fomatDate(e).getTime();
    }


    //获取下周一
    public static String getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return sdfDays.format(cal.getTime());
    }

    public static String geLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return sdfDays.format(cal.getTime());
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }


    //获取指定日期下个月的第一天
    public static String getFirstDayOfNextMonth(String dateStr,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH,1);
            calendar.add(Calendar.MONTH, 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 格式化日期 yyyyMMdd
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date fomatDateyyMMddHHmmss(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期yyyy-MM-dd HH:mm
     * @return
     */
    public static String fomatDate(Date date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return fmt.format(date);
    }

    /**
     * 格式化日期HH:mm
     * @return
     */
    public static String fomatHHmmDate(Date date) {
        DateFormat fmt = new SimpleDateFormat("HH:mm");
        return fmt.format(date);
    }

    /**
     * 校验日期是否合法
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDiffYear(String startTime,String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //long aa=0;
            int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        //System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days,String format) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat(format);
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    //获取日份
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static Date getAfterDayDateToDate(String days,String format) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat(format);
        String dateStr = sdfd.format(date);
        try {
            return sdfd.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    //获取上个月指定天数日期
    public static String getLastMonthAppointDay(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return sdfDays.format(calendar.getTime());
    }

    //获取当前月的第一天
    public static String getMonthFirstDay(){
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return sdfDays.format(cal_1.getTime());
    }


    //获取某年某月的第一天
    public static String getCertainMonthLastDay(String year,String month){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.YEAR,Integer.valueOf(year));
        cal.set(Calendar.MONTH, Integer.valueOf(month));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();
        return sdfTime.format(lastDate);

    }
    //获取某年某月的最后一天
    public static String getCertainMonthFirstDay(String year,String month){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.YEAR,Integer.valueOf(year));
        cal.set(Calendar.MONTH, Integer.valueOf(month));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date first = cal.getTime();
        return sdfTime.format(first);

    }

    //获取当前时间N分钟之后的时间
    public static String getAfterTimes(String time){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,Integer.parseInt(time));
        String afterTime = sdfTimes.format(cal.getTimeInMillis());
        return afterTime;
    }

    //获取当前时间N分钟之后的时间
    public static String getAfterTime(String time){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,Integer.parseInt(time));
        String afterTime = sdfTime.format(cal.getTimeInMillis());
        return afterTime;
    }



    public static void main(String[] args) {
//        System.out.println(getCertainMonthFirstDay("2020","02"));
//        System.out.println(getCertainMonthLastDay("2020","02"));
        String queryDate="201902";
        String year=queryDate.trim().substring(0,4);
        String month=queryDate.trim().substring(4,6);
        System.out.println(year);
        System.out.println(month);

    }

}
