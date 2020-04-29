package com.study.majinhu.designModel.obServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderService
 * @Description 订单服务
 * @Author majinhu
 * @Date 2019/6/12 8:41
 * @Version 1.0
 **/
@Service
public class OrderService {
    @Autowired
    ApplicationContext applicationContext;
    /**
     * @Author majinhu
     * @Description 创建订单
     * @Date 8:45 2019/6/12
     * @Param [name]
     * @return void
     **/
    public  void  saveOrder(String name){

        Order order= new Order();
        order.setOrderNO("001");
        order.setOrderName("娃娃");
        order.setNum(1);
        System.out.println(name+"创建订单"+order);
        //老式方法，顺序执行
        //System.out.println("发送短信");
        //System.out.println("发送邮件");

        //新方法，观察者模式，订阅发布模式；发布一个事件
        applicationContext.publishEvent(new OrderEvent(order));


    }
}
