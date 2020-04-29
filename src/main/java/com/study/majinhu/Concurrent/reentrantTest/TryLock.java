package com.study.majinhu.Concurrent.reentrantTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TryLock
 * @Description
 * 两个线程一个占用lock1锁，一个占用lock2锁，然后又相互请求获取对方的锁，
 * 如果是用lock()的话，则就相互等待，产生死锁。而tryLock()这种轮询的方式，
 * 让他不停的尝试获取，最终两个线程都能成功退出，避免了死锁(可能轮询的时间有点长，
 * 不过最终两个线程是能正常执行结束的)。
 * ---------------------
 * 作者：GokusJQK
 * 来源：CSDN
 * 原文：https://blog.csdn.net/qq_31957747/article/details/74929911
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Author majinhu
 * @Date 2019/6/20 17:18
 * @Version 1.0
 **/
public class TryLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;
    public TryLock(int lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        if(this.lock == 1){
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ":My Job Done");
                                return ;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        }else {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ":My Job Done");
                                return;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TryLock r1 = new TryLock(1);
        TryLock r2 = new TryLock(2);
        new Thread(r1).start();
        new Thread(r2).start();
    }

}
