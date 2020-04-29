package com.study.majinhu.rabbitmq.simple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloReceiver
 * @Description 消息接收
 * @Author majinhu
 * @Date 2019/7/11 16:03
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "q_hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

}
