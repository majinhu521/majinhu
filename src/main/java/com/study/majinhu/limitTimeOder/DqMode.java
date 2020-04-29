package com.study.majinhu.limitTimeOder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.DelayQueue;

/**
 * @ClassName DqMode
 * @Description 限时订单，delayque实现。
 * @Author majinhu
 * @Date 2019/12/18 10:40
 * @Version 1.0
 **/
@Service
public class DqMode {
    private Logger logger = LoggerFactory.getLogger(DqMode.class);

    @Autowired
    private DlyOrderProcessor dlyOrderProcessor;
    /**创建一个延迟队列**/
    private static DelayQueue<ItemVo<Order>> delayOrder = new DelayQueue<>();

    /**订单放入延迟队列**/
    public void orderDelay(Order order,long expireTime){
        ItemVo<Order> itemVo = new ItemVo<>(expireTime,order);
        delayOrder.put(itemVo);
    }

    private class TaskOrder implements Runnable{

        private  DlyOrderProcessor dlyOrderProcessor;

        public TaskOrder(DlyOrderProcessor dlyOrderProcessor) {
            super();
            this.dlyOrderProcessor =dlyOrderProcessor;
        }

        @Override
        public void run() {
            logger.info("处理过期订单线程已启动");
            while(!Thread.currentThread().isInterrupted()){
                try{
                    ItemVo<Order> itemOrder= delayOrder.take();
                    if(itemOrder!=null){
                        dlyOrderProcessor.checkDelayOder(itemOrder.getData());
                    }
                }catch (Exception e){
                    logger.info("Exception");
                }
            }
        }

        private Thread takeOrder;

        @PostConstruct
        public void init(){
            takeOrder = new Thread(new TaskOrder(dlyOrderProcessor));
            takeOrder.start();
        }

        @PreDestroy
        public void close(){
            takeOrder.isInterrupted();
        }

    }



}
