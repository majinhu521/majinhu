package com.study.majinhu.jdkBase.Enum;

/**
 * @ClassName PayType
 * @Description
 * @Author HZY
 * @Date 2020/1/14 10:37
 * @Version 1.0
 **/
public class PayType {
    //支付宝
    public static final String ALI_PAY="1001";
    //微信
    public static final String WE_CHAT="1002";

    public static final String OTHER="9999";

    public static String getPayTypeName(String code){
        String name = "";
        switch (code){
            case ALI_PAY:
                name = "支付宝";
                break;
            case WE_CHAT:
                name = "微信";
                break;
            default:
                break;
        }
        return name;
    }
}