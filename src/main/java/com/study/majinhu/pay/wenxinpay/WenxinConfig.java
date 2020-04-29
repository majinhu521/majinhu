package com.study.majinhu.pay.wenxinpay;
/** * @ClassName WenxinConfig
 * @Description
 * @Author majinhu
 * @Date 2019/8/16 9:58
 * @Version 1.0
 **/
public class WenxinConfig {
    // 1.商户appid
    public static String APP_ID = "2017...";

    //2.MCH_ID c+h+u 大写
    public static String MCH_ID ="MIIEwAIBADANBg.....";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String NOTIFY_URL = "http://www.xxx.com/alipay/notify_url.do";

    public static String SECRIT =" secrit";

    public static String  SPBILL_CREATE_IP ="192.168.0.1";
}
