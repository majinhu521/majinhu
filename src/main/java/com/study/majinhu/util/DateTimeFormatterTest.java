package com.study.majinhu.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ClassName DateTimeFormatterTest
 * @Description
 * 使用DateTimeFormatter代替SimpleDateFormat
 *
 * 　　DateTimeFormatter是线程安全的，默认提供了很多格式化方法，也可以通过ofPattern方法创建自定义格式化方法。
 * https://www.cnblogs.com/yangyongjie/p/11017409.html
 * @Author majinhu
 * @Date 2020/7/25 12:20
 * @Version 1.0
 **/
public class DateTimeFormatterTest {
    public static void main(String[] args){
        //（1）格式化日期示例：
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime); // 2019-11-20T15:04:29.017
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String strDate=localDateTime.format(dtf);
        System.out.println(strDate); // 2019/23/20 15:23:46
        //（2）解析日期
        DateTimeFormatter dtf2=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime2=LocalDateTime.parse("2019/11/20 15:23:46",dtf2);
        System.out.println(localDateTime2); // 2019-11-20T15:23:46

        DateTimeFormatter dtf3=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime3=LocalDateTime.parse("2019-11-20 15:23:46",dtf3);
        System.out.println(localDateTime3); // 2019-11-20T15:23:46

        // 二、使用JDK8全新的日期和时间API
        //1、LocalDate、LocalTime、LocalDateTime
        //　Date如果不格式化，打印出的日期可读性极差，可使用LocalDateTime代替。
        //（1）LocalDateTime：获取年月日时分秒等于LocalDate+LocalTime

        LocalDateTime localDateTime4 = LocalDateTime.now();
        System.out.println("LocalDateTime"+localDateTime4); // 2019-11-20T15:04:29.017
        LocalDate localDate = localDateTime4.toLocalDate();
        LocalTime localTime = localDateTime4.toLocalTime();
        System.out.println(localDate +"==="+localTime);
        //（2）LocalDate：获取年月日

        LocalDate localDate5=LocalDate.now();
        System.out.println(localDate5); // 2019-11-20
        //（3）LocalTime：获取时分秒
        LocalTime localTime6= LocalTime.now();
        System.out.println("LocalTime"+localTime6); // 15:14:17.081
        int hour = localTime6.getHour();
        int minute = localTime6.getMinute();
        int second = localTime6.getSecond();
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);

        Thread t1 = new Thread(() -> {
            LocalDateTime parse = null;
//            try {
                parse = LocalDateTime.parse("2020-12-12 12:12:12",dtf3);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            System.out.println("当前日期：" + parse);
        });

        Thread t2 = new Thread(() -> {
            LocalDateTime parse = null;
//            try {
                parse = LocalDateTime.parse("2019-11-11 11:11:11",dtf3);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            System.out.println("当前日期：" + parse);
        });


        Thread t3 = new Thread(() -> {
            LocalDateTime parse = null;
//            try {
                parse = LocalDateTime.parse("2018-10-10 10:10:10",dtf3);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            System.out.println("当前日期：" + parse);
        });


        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程执行完毕");
    }

}
