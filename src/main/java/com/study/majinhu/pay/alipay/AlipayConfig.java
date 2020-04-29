package com.study.majinhu.pay.alipay;

/**
 * @ClassName AlipayConfig
 * @Description
 * https://blog.csdn.net/Ouyzc/article/details/79551714
 * https://docs.open.alipay.com/204/105465/
 * @Author majinhu
 * @Date 2019/8/15 14:34
 * @Version 1.0
 **/
public class AlipayConfig {
    // 1.商户appid
    public static String APP_ID = "2017...";

    //2.私钥 pkcs8格式的
    public static String APP_PRIVATE_KEY ="MIIEwAIBADANBg.....";

    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkq.....";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String NOTIFY_URL = "http://www.xxx.com/alipay/notify_url.do";

    //5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String RETURN_URL = "http://www.xxx.com/alipay/return_url.do";

    // 6.请求支付宝的网关地址
    public static String GATEWAY_URL = "https://openapi.alipay.com/gateway.do";

    // 7.编码
    public static String CHARSET = "utf-8";

    // 8.返回格式
    public static String FORMAT = "json";

    // 9.加密类型
    public static String SIGN_TYPE = "RSA2";


}
