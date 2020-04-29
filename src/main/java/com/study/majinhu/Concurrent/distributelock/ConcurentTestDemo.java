package com.study.majinhu.Concurrent.distributelock;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName ConcurentTestDemo
 * @Description 多线程模拟锁测试
 * @Author majinhu
 * @Date 2019/7/2 9:41
 * @Version 1.0
 **/
public class ConcurentTestDemo {
    public static void main(String[] args) {
        int currency = 100; //线程数
        CyclicBarrier cb = new CyclicBarrier(currency);
        //不加锁-订单编号会有重复
        OrderService orderService = new OrderServiceImpl();
        //加锁-不会重复，适用于非分布式。单个tomcat部署。
        //OrderService orderService = new OrderServiceWithLockImpl();
        //分布式加锁

        for (int i = 0; i < currency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OrderService orderService = new OrderServiceWithLockImpl();
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
