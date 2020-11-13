package com.study.majinhu.designModel.CommandPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Broker
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:10
 * @Version 1.0
 **/
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}