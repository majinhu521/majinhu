package com.study.majinhu.designModel.Factory.pay;

/**
 * @ClassName UnionPay
 * @Description
 * @Author majinhu
 * @Date 2020/9/7 15:09
 * @Version 1.0
 **/
public class UnionPay extends PayChannel {
    @Override
    public void pay(String price) {
        System.out.println("调起银联SDK，价格：" + price);
    }
}