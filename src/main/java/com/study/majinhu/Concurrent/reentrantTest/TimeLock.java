package com.study.majinhu.Concurrent.reentrantTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TimeLock
 * @Description
 * 定时锁：
 *
 * tryLock(long timeout, TimeUnit unit)接收两个参数，第一个表示等待时间，第二个表示计时单位。
 * 当前线程会尝试获得锁，当前锁没有被线程占用，则获取成功返回true。
 * 被其他线程占用，则等待参数设置的时间，超过这个时间则不等待，立即返回false
 *
 * 一个线程占用了锁，然后sleep6秒，sleep是不会释放锁的，所以另一个线程等待5秒后拿不到锁，在放弃等待了，方法返回false。
 * @Author majinhu
 * @Date 2019/6/20 17:22
 * @Version 1.0
 **/
public class TimeLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName()+"正在运行。并尝试在5秒内获得锁");
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName()+"获取锁，并睡眠6秒");
                Thread.sleep(6000);
            }else{
                System.out.println(Thread.currentThread().getName()+"等待超过5秒。获得锁失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        TimeLock tl = new TimeLock();
        new Thread(tl).start();
        new Thread(tl).start();
    }


}
