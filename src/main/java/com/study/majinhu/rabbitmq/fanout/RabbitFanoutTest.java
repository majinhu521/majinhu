package com.study.majinhu.rabbitmq.fanout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RabbitFanoutTest
 * @Description
 * Fanout Exchange（订阅模式）
 * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息
 *
 * 三个消费者都收到消息：
 * AReceiver : hi, fanout msg
 * CReceiver : hi, fanout msg
 * BReceiver : hi, fanout msg
 * @Author majinhu
 * @Date 2019/7/11 16:39
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitFanoutTest {

    @Autowired
    private MsgSenderFanout msgSender;

    @Test
    public void send1() throws Exception {
        msgSender.send();
    }
}
