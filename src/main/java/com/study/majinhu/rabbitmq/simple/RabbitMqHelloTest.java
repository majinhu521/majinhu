package com.study.majinhu.rabbitmq.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RabbitMqHelloTest
 * @Description 简单rabbitmq测试
 * https://blog.csdn.net/hellozpc/article/details/81436980
 * @Author majinhu
 * @Date 2019/7/11 16:05
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void hello() throws Exception {
        helloSender.send();
    }
}
