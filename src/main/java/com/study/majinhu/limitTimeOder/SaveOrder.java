package com.study.majinhu.limitTimeOder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @ClassName SaveOrder
 * @Description
 * @Author majinhu
 * @Date 2019/12/18 11:28
 * @Version 1.0
 **/
public class SaveOrder {
    private Logger logger = LoggerFactory.getLogger(SaveOrder.class);
    @Autowired
    DqMode delayOrder;
    private void dbsave(){
        long expiretime = 2; //2s 后过期
        Order order = new Order();
        delayOrder.orderDelay(order,expiretime);
    }
    //启动的时候扫码表是否有过期订单。进行处理
    public void initDelayOrder(){
        logger.info("系统启动，扫码表中过期未支付的订单并处理");
        //1.DB UPDATE.
        Order order = new Order();
        logger.info("系统启动，查询未到期，为支付的订单列表。");

        logger.info("系统启动，循环处理未到期，为支付的订单列表，重新放入队列");
        long expiretime = order.getExpireTime().getTime()-(new Date().getTime());
        delayOrder.orderDelay(order,expiretime);
    }
}
