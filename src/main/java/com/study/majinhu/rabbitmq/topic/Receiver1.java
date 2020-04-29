package com.study.majinhu.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Receiver1
 * @Description
 * 创建2个消费者
 * q_topic_message 和q_topic_messages
 * @Author majinhu
 * @Date 2019/7/11 16:25
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "q_topic_message")
public class Receiver1 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }

}

