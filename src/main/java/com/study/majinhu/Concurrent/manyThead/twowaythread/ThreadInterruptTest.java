package com.study.majinhu.Concurrent.manyThead.twowaythread;

/**
 * @ClassName ThreadInterruptTest
 * @Description 线程中断测试
 * @Author majinhu
 * @Date 2020/6/15 16:29
 * @Version 1.0
 **/
public class ThreadInterruptTest extends Thread{

    public ThreadInterruptTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        super.run();
        String name = Thread.currentThread().getName();
        System.out.println("currentThread.getName:"+name);
        System.out.println("当前线程是否中断"+isInterrupted());
    }
    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new ThreadInterruptTest("EndThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
