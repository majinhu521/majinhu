package com.study.majinhu.Concurrent.distributelock;

import com.study.majinhu.Concurrent.distributelock.zk.ZKDistributeLock;
import com.study.majinhu.Concurrent.distributelock.zk.ZKDistributeLockImprove;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName OrderServiceImpl
 * @Description 创建订单实现类
 * @Author majinhu
 * @Date 2019/7/2 9:50
 * @Version 1.0
 **/
public class OrderServiceWithDistributeLockImpl implements OrderService{

    static OrderCodeGenerator og = new OrderCodeGenerator();
    static ZKDistributeLock lock = new ZKDistributeLock("/lock/aaa");

    @Override
    public void createOrder() {
        String orderCode = null;
        lock.tryLock();
        try{
            orderCode = og.getOrderCode();
        }finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName()+"==================>"+orderCode);
    }
}
