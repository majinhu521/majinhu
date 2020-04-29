package com.study.majinhu.Concurrent.countdownAndCyclic;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ExChangeDemo
 * @Description
 * 创建固定线程池数量为2，两个线程以交换字符串数据为例，其中一个线程执行方法exchange()方法时，将等待，
 * 直到第二个线程执行exchange()方法，两个线程完成数据的交换。
 * 如果两个线程有一个没有执行exchange()方法，将会一直等待下去。
 *         作者：DreamTech1113
 *         来源：CSDN
 *         原文：https://blog.csdn.net/lili13897741554/article/details/88861682
 *         版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Author majinhu
 * @Date 2019/7/17 10:14
 * @Version 1.0
 **/
public class ExChangeDemo {
    private static ExecutorService exec = Executors.newFixedThreadPool(2);
    //创建一个Exchange
    private static Exchanger<String> change = new Exchanger<>();
    public static void main(String[] args) {
        exec.execute(new Runnable() {
            public void run() {
                String A="change data--1";
                try {
                    String B = change.exchange(A);
                    System.out.println("Current Thread:"+Thread.currentThread().getName()+",After change:"+B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        exec.execute(new Runnable() {
            public void run() {
                String B="change data--2";
                try {
                    String A=change.exchange(B);
                    System.out.println("Current Thread:"+Thread.currentThread().getName()+",After change:"+A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        exec.shutdown();
    }
}

