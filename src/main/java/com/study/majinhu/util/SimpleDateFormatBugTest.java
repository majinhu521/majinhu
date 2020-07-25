package com.study.majinhu.util;

/**
 * @ClassName SimpleDateFormatBugTest
 * @Description https://blog.csdn.net/qq_33220089/article/details/105216878
 * demo的形式来复现一下SimpleDateFormat存在的bug。
 *当前日期：Thu Dec 12 12:12:12 GMT+08:00 2222
 * Exception in thread "Thread-0" java.lang.NumberFormatException: multiple points
 * 字符串日期转Date日期（parse）
 *
 * **calb.establish(calendar).getTime();**中
 * calendar这个参数是由调用方传递进来的，而调用方parse()拿的是当前类的成员变量。
 *成员变量在多线程情况下如果没有锁的加持，是很容易出现线程安全问题的，他这里是先执行的clear,在重新获取时间
 * getTime最终返回的是成员变量，这个是之前已经设置了值的属性，然而当开启多线程的时候，
 * 很有可能导致第一个线程执行了getTime之后第二个线程有执行了clear，由于这些变量都是成员变量，所以他们是共享的，bug就发生了。
 *
 * @Author majinhu
 * @Date 2020/7/25 10:37
 * @Version 1.0
 **/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatBugTest {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Date parse(String date) {
        Date parse = null;
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            Date parse = parse("2020-12-12 12:12:12");
            System.out.println("当前日期：" + parse);
        });

        Thread t2 = new Thread(() -> {
            Date parse = parse("2020-12-12 12:12:12");
            System.out.println("当前日期：" + parse);
        });


        Thread t3 = new Thread(() -> {
            Date parse = parse("2018-10-10 10:10:10");
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
