package com.study.majinhu.rabbitmq.delaymq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description
 * http://localhost:8088/sendDelay
 * ，查看日志输出 ok
 *
 * 2020-05-28 10:51:21.217  INFO 12632 --- [nio-8088-exec-1] c.s.m.rabbitmq.delaymq.DelaySender       : 【订单生成时间】Thu May 28 10:51:21 GMT+08:00 2020【1分钟后检查订单是否已经支付】Order(orderId=123456, orderStatus=0, orderName=小米6)
 * 2020-05-28 10:51:21.248  INFO 12632 --- [nio-8088-exec-1] c.s.m.rabbitmq.delaymq.DelaySender       : 【订单生成时间】Thu May 28 10:51:21 GMT+08:00 2020【1分钟后检查订单是否已经支付】Order(orderId=456789, orderStatus=1, orderName=小米8)
 * 2020-05-28 10:51:51.293  INFO 12632 --- [ntContainer#0-1] c.s.m.rabbitmq.delaymq.DelayReceiver     : ###########################################
 * 2020-05-28 10:51:51.294  INFO 12632 --- [ntContainer#0-1] c.s.m.rabbitmq.delaymq.DelayReceiver     : 【orderDelayQueue 监听的消息】 - 【消费时间】 - [Thu May 28 10:51:51 GMT+08:00 2020]- 【订单内容】 - [Order(orderId=123456, orderStatus=0, orderName=小米6)]
 * 2020-05-28 10:51:51.294  INFO 12632 --- [ntContainer#0-1] c.s.m.rabbitmq.delaymq.DelayReceiver     : 【该订单未支付，取消订单】Order(orderId=123456, orderStatus=2, orderName=小米6)
 * 2020-05-28 10:51:51.294  INFO 12632 --- [ntContainer#0-1] c.s.m.rabbitmq.delaymq.DelayReceiver     : ###########################################
 * 2020-05-28 10:51:51.296  INFO 12632 --- [ntContainer#0-1] c.s.m.rabbitmq.delaymq.DelayReceiver     : ###########################################
 * 2020-05-28 10:51:51.297  INFO 12632 --- [ntContainer#0-1] c.s.m.rabbitmq.delaymq.DelayReceiver     : 【orderDelayQueue 监听的消息】 - 【消费时间】 - [Thu May 28 10:51:51 GMT+08:00 2020]- 【订单内容】 - [Order(orderId=456789, orderStatus=1, orderName=小米8)]
 * 2020-05-28 10:51:51.297  INFO 12632 --- [ntContainer#0-1] c.s.m.rabbitmq.delaymq.DelayReceiver     : 【该订单已完成支付】
 * 2020-05-28 10:51:51.297  INFO 12632 --- [ntContainer#0-1] c.s.m.rabbitmq.delaymq.DelayReceiver     : ###########################################
 * @Author majinhu
 * @Date 2020/5/28 10:44
 * @Version 1.0
 **/
@RestController
public class TestController {

    @Autowired
    private DelaySender delaySender;

    @GetMapping("/sendDelay")
    public Object sendDelay() {
        Order order1 = new Order();
        order1.setOrderStatus(0);
        order1.setOrderId("123456");
        order1.setOrderName("小米6");

        Order order2 = new Order();
        order2.setOrderStatus(1);
        order2.setOrderId("456789");
        order2.setOrderName("小米8");

        delaySender.sendDelay(order1);
        delaySender.sendDelay(order2);
        return "ok";
    }
}
