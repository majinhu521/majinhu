package com.study.majinhu.Concurrent.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName LockSupportUse
 * @Description
 * @Author majinhu
 * @Date 2019/6/27 14:49
 * @Version 1.0
 **/
public class LockSupportUse {
    public static void main(String[] args) {
        try {
            testParkThenInterrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testParkThenInterrupt() throws InterruptedException {
        Thread thread = new Thread(new LockSupportThread(), "LockSupportThread");
        thread.start();

        Thread.sleep(2);
        //充分运行线程LockSupportThread两秒钟后，中断该线程，
        //该线程能从park()方法返回
        thread.interrupt();
        LockSupport.unpark(thread);
    }

    static class LockSupportThread implements Runnable{
        @Override
        public void run() {
            LockSupport.park(); //阻塞自己
            System.out.println(Thread.currentThread().getName() + "从park()中返回");
        }
    }
}
