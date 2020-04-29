package com.study.majinhu.Concurrent.reentrantTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName InterruptiblyLock
 * @Description 中断锁
 * 两个线程相互等待锁的问题，这时候中断了其中一个线程，另一个线程也就能顺利的拿到锁并执行结束
 * @Author majinhu
 * @Date 2019/6/20 17:28
 * @Version 1.0
 **/
public class InterruptiblyLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;
    /**
     * 控制加锁顺序，方便构造死锁
     * @param lock
     */
    public InterruptiblyLock(int lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        try{
            if(lock == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            }else{
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
            System.out.println(Thread.currentThread().getId()+":线程执行完任务");
        }catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getId()+":线程被中断");
        }finally {
            if(lock1.isHeldByCurrentThread())
                lock1.unlock();
            if(lock2.isHeldByCurrentThread())
                lock2.unlock();
            System.out.println(Thread.currentThread().getId()+":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptiblyLock r1 = new InterruptiblyLock(1);
        InterruptiblyLock r2 = new InterruptiblyLock(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //中断其中一个线程
        t2.interrupt();
    }


}
