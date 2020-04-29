package com.study.majinhu.Concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName ParkUnparkTest
 * @Description
 * @Author majinhu
 * @Date 2019/6/19 13:45
 * @Version 1.0
 **/
public class ParkUnparkTest {
    public static void main(String[] args) throws InterruptedException {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("child thread begin park!");
                    // 调用park方法，挂起自己
                   // LockSupport.park();
                    while (!Thread.currentThread().isInterrupted()) {
                        LockSupport.park();
                    }
                        System.out.println("child thread unpark!");
                }
            });
            thread.start(); //启动子线程
            Thread.sleep(2000); //主线程休眠1S
            System.out.println("main thread begin unpark!");
            //调用unpark让thread线程持有许可证，然后park方法会返回
            //LockSupport.unpark(thread);

            thread.interrupt();
//                child thread begin park!
//                main thread begin unpark!
//                child thread unpark!
    }

}
