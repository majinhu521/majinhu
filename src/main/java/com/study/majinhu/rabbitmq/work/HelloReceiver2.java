package com.study.majinhu.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloReceiver2
 * @Description
 * @Author majinhu
 * @Date 2019/7/11 16:15
 * @Version 1.0
 **/
//@Component
//@RabbitListener(queues = "q_hello")
public class HelloReceiver2 {

//    @RabbitHandler
//    public void process(String hello) {
//        System.out.println("Receiver2  : " + hello);
//    }
}
