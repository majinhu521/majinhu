package com.study.majinhu.Concurrent;

/**
 * @ClassName NotifyTest
 * @Description  唤醒测试
 *
 *开启了三个线程，其中线程 A 和 B 分别调用了共享资源 resourceA 的 wait() 方法，线程 C 则调用了 nofity() 方法。
 *nofity() 方法在共享变量上调用一次就会唤醒在该共享变量上调用 wait 系列方法被挂起的一个线程，
 * notifyAll() 则会唤醒所有在该共享变量上由于调用 wait 系列方法而被挂起的线程。
 *
 *
 * @Author majinhu
 * @Date 2019/6/19 10:10
 * @Version 1.0
 **/
public class NotifyTest {
    private static volatile Object resourceA = new Object();
    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                // 获取resourceA共享资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");
                    try {
                        System.out.println("threadA begin wait");
                        resourceA.wait();
                        System.out.println("threadA end wait");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });    // 创建线程
        Thread threadB = new Thread(new Runnable() {
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                    try {
                        System.out.println("threadB begin wait");
                        resourceA.wait();
                        System.out.println("threadB end wait");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        });
        // 创建线程
        Thread threadC = new Thread(new Runnable() {
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadC begin notify");
                    resourceA.notify();
                    //resourceA.notifyAll();
                }
            }
        });
        // 启动线程
        threadA.start();
        threadB.start();

        Thread.sleep(1000);
        threadC.start();    // 等待线程结束
        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("main over");
    }
}
