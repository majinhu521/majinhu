package com.study.majinhu.Concurrent.LockSupport;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReentrantReadWriteLockTest
 * @Description
 * ReadWriteLock是JDK5开始提供的读写分离锁。读写分离开有效的帮助减少锁的竞争，以提升系统性能。用锁分离的机制避免多个读操作线程之间的等待。
 *
 * 读写锁的访问约束：
 *
 * 读-读不互斥：读读之间不阻塞
 * 读-写互斥：读堵塞写，写也阻塞读
 * 写-写互斥：写写阻塞
 * 如果在一个系统中读的操作次数远远大于写操作，那么读写锁就可以发挥明显的作用，提升系统性能
 * https://www.cnblogs.com/whatadiors/p/8013086.html
 * @Author majinhu
 * @Date 2019/6/27 15:04
 * @Version 1.0
 **/
public class ReentrantReadWriteLockTest {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    private static int value;

    public static Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);// 模拟读操作
            System.out.println("读操作:" + value);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public static void handleWrite(Lock lock, int index)
            throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);// 模拟写操作
            System.out.println("写操作:" + value);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        TestReadThread testReadThread = new TestReadThread();
        TestWriteThread testWriteThread = new TestWriteThread();
        for (int i = 0; i < 18; i++) {
            new Thread(testReadThread).start();
        }
        for (int i = 18; i < 20; i++) {
            new Thread(testWriteThread).start();
        }

    }

    private static class TestReadThread extends Thread {
        @Override
        public void run() {
            try {
                //ReentrantReadWriteLockTest.handleRead(lock);
               ReentrantReadWriteLockTest.handleRead(readLock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class TestWriteThread extends Thread {
        @Override
        public void run() {
            try {
               // ReentrantReadWriteLockTest.handleWrite(lock,new Random().nextInt(100));
                ReentrantReadWriteLockTest.handleWrite(writeLock,new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
