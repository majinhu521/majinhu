package com.study.majinhu.designModel.Factory.pay;

/**
 * @ClassName WechatPay
 * @Description
 * @Author majinhu
 * @Date 2020/9/7 15:10
 * @Version 1.0
 **/
public class WechatPay extends PayChannel {
    @Override
    public void pay(String price) {
        System.out.println("调起微信SDK，价格：" + price);
    }
}