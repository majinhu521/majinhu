package com.study.majinhu.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName ReceiverC
 * @Description 消费者C
 * @Author majinhu
 * @Date 2019/7/11 16:36
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "q_fanout_C")
public class ReceiverC {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("CReceiver  : " + hello + "/n");
    }
}
