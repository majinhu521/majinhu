package com.study.majinhu.Concurrent.manyThead;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadStateDemo
 * @Description
 * https://blog.csdn.net/weixin_44761260/article/details/113510860?utm_medium=distribute.pc_category.none-task-blog-hot-11.nonecase&dist_request_id=443e53ed-7722-4e2d-86c5-1f55266a4864&depth_1-utm_source=distribute.pc_category.none-task-blog-hot-11.nonecase
 * @Author majinhu
 * @Date 2021/2/20 16:07
 * @Version 1.0
 **/
public class ThreadStateDemo {

    private int i = 10;

    public static void main(String[] args) throws InterruptedException {
        waitDemo();
        final ThreadStateDemo stateDemo = new ThreadStateDemo();
        // NEW 新创建的线程
        final Thread thread = new ThreadState(stateDemo);
        // RUNNABLE 可以运行了
        System.out.println("thread.getState() = " + thread.getState());
        thread.start();
        System.out.println("thread.getState() = " + thread.getState());
        final Thread thread2 = new ThreadState(stateDemo);
        thread2.start();
        TimeUnit.SECONDS.sleep(2);
        // BLOCKED  获取锁失败
        System.out.println("thread2.getState() = " + thread2.getState());
        TimeUnit.SECONDS.sleep(200);
        // TERMINATED
        System.out.println("thread.getState() = " + thread.getState());
    }

    /**
     * 演示WAITING
     *
     * @throws InterruptedException
     */
    static void waitDemo() throws InterruptedException {
        final ThreadStateDemo stateDemo = new ThreadStateDemo();
        // 死循环减少i
        final Thread cut1 = new ThreadStateCut(stateDemo);
        final Thread cut2 = new ThreadStateCut(stateDemo);
        cut1.start();
        cut2.start();
        TimeUnit.SECONDS.sleep(11);
        System.out.println("cut1 = " + cut1.getState());
        System.out.println("cut2 = " + cut2.getState());

        // 增加i
        final Thread add = new ThreadStateAdd(stateDemo);
        add.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("cut1 = " + cut1.getState());
        System.out.println("cut2 = " + cut2.getState());

        TimeUnit.SECONDS.sleep(20);

        System.exit(1086);
    }

    public synchronized void print() {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is run;");
    }

    public synchronized void intCut() {
        try {
            if (i >= 0) {
                TimeUnit.SECONDS.sleep(1);
                i--;
                System.out.println(Thread.currentThread().getName() + i + "-- 了");
            } else {
                //                wait();
                wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void intAdd() {
        i += 10;
        notifyAll();
    }

}

class ThreadState extends Thread {

    private final ThreadStateDemo print;


    ThreadState(ThreadStateDemo print) {this.print = print;}

    @Override
    public void run() {
        print.print();
    }
}

class ThreadStateCut extends Thread {

    private final ThreadStateDemo print;


    ThreadStateCut(ThreadStateDemo print) {this.print = print;}

    @Override
    public void run() {
        while (true) {
            print.intCut();
        }
    }
}

class ThreadStateAdd extends Thread {

    private final ThreadStateDemo print;


    ThreadStateAdd(ThreadStateDemo print) {this.print = print;}

    @Override
    public void run() {
        print.intAdd();
    }


}