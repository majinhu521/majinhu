package com.study.majinhu.designModel.Factory.pay;

/**
 * @ClassName PayTest
 * @Description
 * @Author majinhu
 * @Date 2020/9/7 15:11
 * @Version 1.0
 **/
public class PayTest {
    public static void main(String[] args) {

        /*
        PayChannel payChannel = PayFactory.getPayChannel("aliy");
        payChannel.pay("100元");

        PayChannel payChannel = PayFactory.getPayChannel("union");
        payChannel.pay("100元");
        */

        PayChannel payChannel = PayFactory.getPayChannel("wechat");
        payChannel.pay("100元");

        PayChannel payChannel2 = PayFactory2.getPayChannel(AliyPay.class);
        payChannel2.pay("200元");

    }
}
