package com.study.majinhu.rabbitmq.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RabbitTopicTest
 * @Description
 * Topic Exchange（主题模式）
 *
 * topic 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列
 * 首先对topic规则配置，这里使用两个队列(消费者)来演示。
 *
 * send1方法会匹配到topic.#和topic.message，两个Receiver都可以收到消息，
 * 发送send2只有topic.#可以匹配所有只有Receiver2监听到消息。
 * @Author majinhu
 * @Date 2019/7/11 16:29
 * @Version 1.0
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RabbitTopicTest {

//    @Autowired
//    private MsgSender msgSender;
//
//    @Test
//    public void send1() throws Exception {
//        msgSender.send1();
//    }
//
//    @Test
//    public void send2() throws Exception {
//        msgSender.send2();
//    }
}
