package com.study.majinhu.Concurrent.ABA;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName TestIjiajia
 * https://blog.csdn.net/qq_37212210/article/details/92815391
 * @Description x 作为全局变量，使用 x++ 线程安全吗.不安全。
 * 输出的结果具有随机性，有可能是100000也有可能小于100000。由此可以看出，
 * 使用 x++ 操作在多线程环境下导致数据错误，也就是说使用 x++ 线程不安全！
 *
 * 那如果使用AtomicInteger原子类呢？
 * 1.获取变量的当前值
 * 2.变量当前值加1
 * 3.将新的值赋值给变量
 *
 * 假设现在只有A、B两个线程同时访问共享变量 x ，并发执行 x++ 操作。
 * 当线程A访问变量 x 的值为0，并加1，但没来得及将新的值赋值给共享变量 x ，
 * 线程B就访问共享变量 x ，此时 x 的值依然为0，然后线程A、线程B分别执行剩下的指令后发现，
 * 线程A和线程B的两次 x++ 操作的结果为共享变量x 的值为1。
 * 可以使用sychronized或者使用Lock显式加锁解决，也可以使用AtomicInteger类更加优雅的解决，
 * 因为java.util.concurrent.atomic包下的类都能够保证原子性,而且最大的好处就是性能，
 * 因为使用AtomicInteger是无锁的，对计算机而言，避免了加锁、解锁和线程上下文切换的操作。
 * ---------------------
 * 作者： MuggleLee
 * 来源：CSDN
 * 原文：https://blog.csdn.net/qq_37212210/article/details/92815391
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Author majinhu
 * @Date 2019/6/27 10:22
 * @Version 1.0
 **/
public class TestIjiajia {
    //private static int x = 0;
    static AtomicInteger x = new AtomicInteger(0);

    private static final Integer THREAD_NUM = 100000;

    //设置栅栏是为了防止循环还没结束就执行main线程输出自增的变量，导致误以为线程不安全
    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < THREAD_NUM; j++) {
            new Thread(() -> {
                add();
            }).start();
        }
        countDownLatch.await();
        System.out.println("X的值：" + x);
    }

    public static void add(){
        //x++;
        x.getAndIncrement();
        countDownLatch.countDown();
    }
}
