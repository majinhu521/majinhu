package com.study.majinhu.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName MsgSender
 * @Description
 * send1方法会匹配到topic.#和topic.message，
 * 两个Receiver都可以收到消息，发送send2只有topic.#可以匹配所有只有Receiver2监听到消息。
 * @Author majinhu
 * @Date 2019/7/11 16:27
 * @Version 1.0
 **/
//@Component
public class MsgSender {

//    @Autowired
//    private AmqpTemplate rabbitTemplate;
//
//    public void send1() {
//        String context = "hi, i am message 1";
//        System.out.println("Sender : " + context);
//        this.rabbitTemplate.convertAndSend("mybootexchange", "topic.message", context);
//    }
//
//
//    public void send2() {
//        String context = "hi, i am messages 2";
//        System.out.println("Sender : " + context);
//        this.rabbitTemplate.convertAndSend("mybootexchange", "topic.messages", context);
//    }
}
