package com.study.majinhu.designModel.Factory.pay;

/**
 * @ClassName PayFactory
 * @Description
 * @Author majinhu
 * @Date 2020/9/7 15:10
 * @Version 1.0
 **/
public class PayFactory {
    public static PayChannel getPayChannel(String payType) {
        PayChannel payChannel = null;
        switch (payType) {
            case "wechat":
                payChannel = new WechatPay();
                break;
            case "aliy":
                payChannel = new AliyPay();
                break;
            case "union":
                payChannel = new UnionPay();
                break;
        }
        return payChannel;
    }
}
