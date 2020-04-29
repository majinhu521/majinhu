package com.study.majinhu.Concurrent.reentrantTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName FairLockTest
 * @Description 公平锁：创建三个线程，模拟公平锁。
 *
 * @Author majinhu
 * @Date 2019/6/20 17:12
 * @Version 1.0
 **/
public class FairLockTest implements Runnable{
    //每个线程轮询获取一次，获取完在释放。下一个线程获取。
    //ReentrantLock fairLock = new ReentrantLock(true);
    //默认是非公平锁。
    ReentrantLock fairLock = new ReentrantLock();
    @Override
    public void run() {
        while(true){
            try{
                fairLock.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId()+" 获得锁");
            }finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLockTest ft = new FairLockTest();
        ExecutorService es = Executors.newFixedThreadPool(3);
        for(int i = 0;i<3;i++) {
            es.submit(ft);
        }
    }


}
