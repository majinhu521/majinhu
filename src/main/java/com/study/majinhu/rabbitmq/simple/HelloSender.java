package com.study.majinhu.rabbitmq.simple;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName HelloSender
 * @Description 发送者
 * @Author majinhu
 * @Date 2019/7/11 16:02
 * @Version 1.0
 **/
public class HelloSender {
//    @Autowired
//    private AmqpTemplate rabbitTemplate;
//
//    public void send() {
//        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
//        String context = "hello " + date;
//        System.out.println("Sender : " + context);
//        //简单对列的情况下routingKey即为Q名
//        this.rabbitTemplate.convertAndSend("q_hello", context);
//    }

}
