package com.study.majinhu.Concurrent.reentrantTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantTest
 * @Description 可重入性-获取2次锁，不死锁。
 * https://blog.csdn.net/qq_31957747/article/details/74929911
 * @Author majinhu
 * @Date 2019/6/20 17:03
 * @Version 1.0
 **/
public class ReentrantTest implements Runnable {
    ReentrantLock lock = new ReentrantLock();
    public void doSomething() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+" doSomething()");
            System.out.println("进入第一个lock,当前的lock数："+lock.getHoldCount());
            doAnotherThing();
        } finally {
            lock.unlock();
            System.out.println("第一个lock释放,当前的lock数："+lock.getHoldCount());
        }
    }

    private void doAnotherThing() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+" doAnotherThing()");
            System.out.println("进入第二个lock,当前的lock数："+lock.getHoldCount());
        } finally {
            lock.unlock();
            System.out.println("第二个lock释放,当前的lock数："+lock.getHoldCount());
        }
    }

    @Override
    public void run() {
        doSomething();
    }
    public static void main(String[] args) {
        ReentrantTest rt = new ReentrantTest();
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i = 0;i<5;i++) {
            es.submit(rt);
        }
        es.shutdown();
    }

}
