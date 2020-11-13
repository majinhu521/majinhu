package com.study.majinhu.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
/**
 * @ClassName SimpleDateFormatThreadLocal
 * @Description
 * @Author majinhu
 * @Date 2020/7/25 11:10
 * @Version 1.0
 **/
public class SimpleDateFormatThreadLocal {



    private static ThreadLocal<SimpleDateFormat> t = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


    private  static SimpleDateFormat getSimpleDateFormat() {
        return t.get();
    }


    private static Date parse(String date){
        try {
            return getSimpleDateFormat().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String format(Date date){
            return getSimpleDateFormat().format(date);
    }

    /**
     * 多线程下格式化时间的问题。
     */
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static Date threadLocalParse(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }
    public static String threadLocalFormat(Date date) {
        return threadLocal.get().format(date);
    }

    public static void clear() {
        t.remove();
    }

    public static void main(String[] args) {

//        Thread t1 = new Thread(() -> {
//            Date parse = parse("2020-12-12 12:12:12");
//            System.out.println("当前日期：" + parse);
//        });
//
//        Thread t2 = new Thread(() -> {
//            Date parse = parse("2019-11-11 11:11:11");
//            System.out.println("当前日期：" + parse);
//        });
//
//
//        Thread t3 = new Thread(() -> {
//            Date parse = parse("2018-10-10 10:10:10");
//            System.out.println("当前日期：" + parse);
//        });

        Thread t1 = new Thread(() -> {
            Date parse = null;
            try {
                parse = threadLocalParse("2020-12-12 12:12:12");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("当前日期：" + parse);
        });

        Thread t2 = new Thread(() -> {
            Date parse = null;
            try {
                parse = threadLocalParse("2019-11-11 11:11:11");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("当前日期：" + parse);
        });


        Thread t3 = new Thread(() -> {
            Date parse = null;
            try {
                parse = threadLocalParse("2018-10-10 10:10:10");
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
