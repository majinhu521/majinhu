package com.study.majinhu.Concurrent.distributelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName OrderServiceImpl
 * @Description 创建订单实现类
 * @Author majinhu
 * @Date 2019/7/2 9:50
 * @Version 1.0
 **/
public class OrderServiceWithLockImpl implements OrderService{

    static OrderCodeGenerator og = new OrderCodeGenerator();
    static Lock lock = new ReentrantLock();

    @Override
    public void createOrder() {
        String orderCode = null;
        lock.lock();
        try{
            orderCode = og.getOrderCode();
        }finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName()+"==================>"+orderCode);
    }
}
