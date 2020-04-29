package com.study.majinhu.Concurrent.distributelock;

import com.study.majinhu.Concurrent.distributelock.zk.ZKDistributeLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName ConcurentTestDemo
 * @Description 多线程模拟锁测试
 * @Author majinhu
 * @Date 2019/7/2 9:41
 * @Version 1.0
 **/
public class ConcurentDistributeTestDemo {
    public static void main(String[] args) {
        int currency = 2; //线程数
        CyclicBarrier cb = new CyclicBarrier(currency);
        //分布式加锁
        OrderService orderService = new OrderServiceWithDistributeLockImpl();

        for (int i = 0; i < currency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                   // OrderService orderService = new OrderServiceWithDistributeLockImpl();
//                    OrderService orderService = new OrderServiceWithDistributeLockImpl();
                   // System.out.println(Thread.currentThread().getName()+"==========ready==========");
                    try {
                        cb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    orderService.createOrder();

                    //System.out.println(Thread.currentThread().getName()+"========================>");
                }
            }).start();

        }
    }
}
