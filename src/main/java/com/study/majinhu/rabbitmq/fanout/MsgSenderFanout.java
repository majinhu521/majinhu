package com.study.majinhu.rabbitmq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName MsgSenderFanout
 * @Description 生产者
 * @Author majinhu
 * @Date 2019/7/11 16:38
 * @Version 1.0
 **/
@Component
public class MsgSenderFanout {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hi, fanout msg ";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("mybootfanoutExchange","", context);
    }
}

