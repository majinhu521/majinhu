package com.study.majinhu.rabbitmq.delaymq;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName DelayReceiver
 * @Description https://www.jianshu.com/p/2c5eebfd0e95 消息的确认
 * @Author majinhu
 * @Date 2020/5/28 10:41
 * @Version 1.0
 **/

//    @Component
//    @Slf4j
public class DelayReceiver {

//        @RabbitListener(queues = {DelayRabbitConfig.ORDER_QUEUE_NAME})
//        public void orderDelayQueue(Order order, Message message, Channel channel) {
//            log.info("###########################################");
//            log.info("【orderDelayQueue 监听的消息】 - 【消费时间】 - [{}]- 【订单内容】 - [{}]", new Date(), order.toString());
//            if (order.getOrderStatus() == 0) {
//                order.setOrderStatus(2);
//                log.info("【该订单未支付，取消订单】" + order.toString());
//            } else if (order.getOrderStatus() == 1) {
//                log.info("【该订单已完成支付】");
//            } else if (order.getOrderStatus() == 2) {
//                log.info("【该订单已取消】");
//            }
//            log.info("###########################################");
//        }
    }