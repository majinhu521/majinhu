package com.study.majinhu.designModel.obServer;

/**
 * @ClassName Order
 * @Description 订单类
 * @Author majinhu
 * @Date 2019/6/12 15:07
 * @Version 1.0
 **/
public class Order {
    //订单编号
    private String orderNO;
    //订单名称
    private String orderName;
    //订单数量
    private int num;

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNO='" + orderNO + '\'' +
                ", orderName='" + orderName + '\'' +
                ", num=" + num +
                '}';
    }
}
