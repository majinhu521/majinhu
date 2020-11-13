package com.study.majinhu.Concurrent.lock.enjoylock;

import java.util.concurrent.locks.Lock;


/**
 * @ClassName PushOrder
 * @Description 自己实现的锁进行测试
 * @Author majinhu
 * @Date 2020/5/21 9:06
 * @Version 1.0
 **/
public class PushOrderMajinhuLock {
    int i = 60000;
    Lock lock = new MajinhuLock();
    public  void order(){
        lock.lock();
        try{
            i--;
        }finally {
            lock.unlock();
        }

    }
    public static void main(String[] args) throws InterruptedException {
        //开启6个线程，每个线程执行10000次减1的操作。
        PushOrderMajinhuLock pushOrder = new PushOrderMajinhuLock();
        for (int j = 0; j < 6; j++) {
            new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    pushOrder.order();
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println("库存量"+pushOrder.i);
    }
}
