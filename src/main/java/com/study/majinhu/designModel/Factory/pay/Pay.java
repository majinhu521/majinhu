package com.study.majinhu.designModel.Factory.pay;

/**
 * @ClassName Pay
 * @Description https://www.jianshu.com/p/b3600a262c38
 * @Author majinhu
 * @Date 2020/9/7 15:08
 * @Version 1.0
 **/
public class Pay {
    public void pay(String price, String payType) {
        switch (payType) {
            case "wechat":
                startAilyPay(price);
                break;
            case "aliy":
                startWechatPay(price);
                break;
            case "union":
                startUnionPay(price);
                break;
        }
    }

    private void startWechatPay(String price) {
        System.out.println("调起微信SDK，价格：" + price);
    }

    private void startAilyPay(String price) {
        System.out.println("调起支付宝SDK，价格：" + price);
    }

    private void startUnionPay(String price) {
        System.out.println("调起银联SDK，价格：" + price);
    }

}
