package com.study.majinhu.designModel.Factory.pay;

/**
 * @ClassName AliyPay
 * @Description
 * @Author majinhu
 * @Date 2020/9/7 15:09
 * @Version 1.0
 **/
public class AliyPay extends PayChannel {
    @Override
    public void pay(String price) {
        System.out.println("调起支付宝SDK，价格：" + price);
    }
}