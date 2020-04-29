package com.study.majinhu.Concurrent.countdownAndCyclic;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchTest
 * @Description
 * CountDownLatch(倒计数器)
 *
 * 作用：latch(门闩)，门闩的含义:把门锁起来，不让里面的线程跑出来，
 * 因此，这个工具通常用来控制线程等待，它可以让某个线程等待直到倒计时结束，再开始执行。
 *
 * CountDownLatch的构造函数接收一个整数N作为参数，即当前这个计数器个数。
 * ---------------------
 * 作者：chenkaibsw
 * 来源：CSDN
 * 原文：https://blog.csdn.net/chenkaibsw/article/details/80937189
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 * https://blog.csdn.net/chenkaibsw/article/details/80937189
 * @Author majinhu
 * @Date 2019/7/17 8:58
 * @Version 1.0
 **/
public class CountDownLatchTest {

    static CountDownLatch c = new CountDownLatch(2);//初始化计数器个数为2

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();                //计数器里面值减去一
                System.out.println(2);
                c.countDown();
            }
        }).start();

        c.await();//CountDownLatch的await()方法阻塞当前线程，计数到0时，当前线程继续执行
        System.out.println("3");

    }
}