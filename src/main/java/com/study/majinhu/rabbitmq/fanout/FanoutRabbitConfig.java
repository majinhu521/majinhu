package com.study.majinhu.rabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FanoutRabbitConfig
 * @Description
 * @Author majinhu
 * @Date 2019/7/11 16:33
 * @Version 1.0
 **/
//@Configuration
public class FanoutRabbitConfig {

//    @Bean
//    public Queue aMessage() {
//        return new Queue("q_fanout_A");
//    }
//
//    @Bean
//    public Queue bMessage() {
//        return new Queue("q_fanout_B");
//    }
//
//    @Bean
//    public Queue cMessage() {
//        return new Queue("q_fanout_C");
//    }
//
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange("mybootfanoutExchange");
//    }
//
//    @Bean
//    Binding bindingExchangeA(Queue aMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(aMessage).to(fanoutExchange);
//    }
//
//    @Bean
//    Binding bindingExchangeB(Queue bMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(bMessage).to(fanoutExchange);
//    }
//
//    @Bean
//    Binding bindingExchangeC(Queue cMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(cMessage).to(fanoutExchange);
//    }
}
