package com.study.majinhu.designModel.Factory.pay;

/**
 * @ClassName PayFactory2
 * @Description
 * @Author majinhu
 * @Date 2020/9/7 15:11
 * @Version 1.0
 **/
public class PayFactory2 {
    public static PayChannel getPayChannel(Class<? extends PayChannel> clz){
        PayChannel payChannel = null;
        try {
            payChannel = (PayChannel) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payChannel;
    }
}
