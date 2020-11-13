package com.study.majinhu.util;

/**
 * @ClassName MyLocalThread
 * @Description
 * 就是每个线程的第二次获取的数据和第一次是相同的，而且不会和其他线程发生任何错乱，这就是ThreadLocal的神奇之处。
 * 线程名：Thread-0 7
 * 线程名：Thread-2 10
 * 线程名：Thread-2 第二次获取 10
 * 线程名：Thread-1 8
 * 线程名：Thread-0 第二次获取 7
 * 线程名：Thread-1 第二次获取 8
 * 执行完毕
 * @Author majinhu
 * @Date 2020/7/25 11:03
 * @Version 1.0
 **/
import java.util.Random;

public class MyLocalThread {


    private static Random random = new Random();

    private static ThreadLocal<Integer> t = ThreadLocal.withInitial(
            ()->random.nextInt(10)+1
    );



    private static Integer get(){
        return t.get();
    }


    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            Integer num = get();
            System.out.println("线程名："+Thread.currentThread().getName()+" "+ num);
            num = get();
            System.out.println("线程名："+Thread.currentThread().getName()+" 第二次获取 "+ num);
        });

        Thread t2 = new Thread(() -> {
            Integer num = get();
            System.out.println("线程名："+Thread.currentThread().getName()+" "+ num);
            num = get();
            System.out.println("线程名："+Thread.currentThread().getName()+" 第二次获取 "+ num);
        });

        Thread t3 = new Thread(() -> {
            Integer num = get();
            System.out.println("线程名："+Thread.currentThread().getName()+" "+ num);
            num = get();
            System.out.println("线程名："+Thread.currentThread().getName()+" 第二次获取 "+ num);
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

        System.out.println("执行完毕");
    }


}