package com.study.majinhu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @ClassName SimpleDateFormatTest2
 * @Description
 *使用三个线程分别对2020-12-12 12:12:12、2019-11-11 11:11:11、2018-10-10 10:10:10的Date格式转字符串格式的操作，
 * 我们在静态代码块中初始化了Date格式的数据，然后使用三个线程分别对他们进行格式转换。
 * 当前日期：2018-10-10 10:10:10
 * 当前日期：2019-10-10 10:10:10
 * 当前日期：2019-10-10 10:10:10
 * @Author majinhu
 * @Date 2020/7/25 10:43
 * @Version 1.0
 **/
public class SimpleDateFormatTest2 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private static Date d1 = null;

    private static Date d2 = null;

    private static Date d3 = null;

    static {
        try {
            d1 = sdf.parse("2020-12-12 12:12:12");
            System.out.println("static d1"+d1);
            d2 =sdf.parse("2019-11-11 11:11:11");
            System.out.println("static d2"+d2);
            d3 =sdf.parse("2018-10-10 10:10:10");
            System.out.println("static d3"+d3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            String parse = sdf.format(d1);
            System.out.println("当前日期：" + parse);
        });

        Thread t2 = new Thread(() -> {
            String parse = sdf.format(d2);
            System.out.println("当前日期：" + parse);
        });


        Thread t3 = new Thread(() -> {
            String parse = sdf.format(d3);
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

    }
}
