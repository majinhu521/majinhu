package com.study.majinhu.designModel.obServer;

import org.checkerframework.checker.units.qual.A;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName SmsListener
 * @Description
 * 监听者：实现的方法
 * 方法一：类implements ApplicationListener， @Override重写onApplicationEvent（事件，或者对象），
 * 方法二：或者方法上加@EventListener，
 * 需要开启异步线程池通道，否则是主线程执行。
 * 可在方法上加   @Async 或者 启动类加@EnableAsync
 * 具体是否需要异步线程池来处理待测试，可看一下如下网页。
 * https://www.jianshu.com/p/74f00fb7e758
 * https://www.lovecto.cn/20180814/205.html
 * @Author majinhu
 * @Date 2019/6/12 8:52
 * @Version 1.0
 **/
@Component
public  class EmailListener {

    @EventListener
    @Async
    public void onApplicationEvent(OrderEvent orderEvent) {
        System.out.println("EmailListener发送email"+orderEvent.getSource());
        Order order= (Order) orderEvent.getSource();
        System.out.println("获取到了订单名称"+order.getOrderName());

    }

}
