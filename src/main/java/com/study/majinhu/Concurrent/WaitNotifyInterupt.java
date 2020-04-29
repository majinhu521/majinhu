package com.study.majinhu.Concurrent;

/**
 * @ClassName WaitNotifyInterupt
 * @Description  测试线程中断
 * threadA 调用了共享对 obj 的 wait（） 方法后阻塞挂起了自己，然后主线程在休眠1s后中断了 threadA 线程，
 * 可知中断后 threadA 在 obj.wait() 处抛出了 java.lang.IllegalMonitorStateException 异常后返回后终止
 * @Author majinhu
 * @Date 2019/6/19 10:04
 * @Version 1.0
 **/
public class WaitNotifyInterupt {
    static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        //创建线程
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("---begin---");                    //阻塞当前线程
                    obj.wait();
                    System.out.println("---end---");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
        Thread.sleep(1000);
        System.out.println("---begin interrupt threadA---");
        threadA.interrupt();
        System.out.println("---end interrupt threadA---");
    }




}