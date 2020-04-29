package com.study.majinhu.Concurrent.distributelock;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description 创建订单实现类
 * @Author majinhu
 * @Date 2019/7/2 9:50
 * @Version 1.0
 **/
public class OrderServiceImpl implements OrderService{
    OrderCodeGenerator og = new OrderCodeGenerator();

    @Override
    public void createOrder() {
        String orderCode = og.getOrderCode();

        System.out.println(Thread.currentThread().getName()+"==================>"+orderCode);
    }
}
