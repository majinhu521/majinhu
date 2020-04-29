package com.study.majinhu.pay.wenxinpay;

import com.study.majinhu.idgen.IdGenerate;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName WeixinPayUtil
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 11:02
 * @Version 1.0
 **/
public class WeixinPayUtil {
    /**
     * @Author majinhu
     * @Description 发起支付
     * @Date 10:20 2019/8/16
     * @Param [orderNo, TotalMoney, orderType]
     * @return java.lang.String
     **/
    public static String add(String orderNo,String TotalMoney,String orderType) throws Exception {
        URL postUrl = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
        HttpsURLConnection con = (HttpsURLConnection) postUrl.openConnection();//打开连接
        con.setRequestMethod("POST");//post方式提交
        con.setDoOutput(true);//打开读写属性，默认均为false
        con.setDoInput(true);
        con.setUseCaches(false);//Post请求不能使用缓存
        con.setInstanceFollowRedirects(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        Map<String, Object> vo = new HashMap<String, Object>();
        //相关参数,可以定义一个Map做成动态的
        vo.put("appid", WenxinConfig.APP_ID);
        vo.put("attach", "支付测试");
        vo.put("body", "APP应用支付");
        vo.put("mch_id", WenxinConfig.MCH_ID);
        vo.put("nonce_str", IdGenerate.uuid());
        String notifyUrl=WenxinConfig.NOTIFY_URL;
        notifyUrl=notifyUrl+"/interface/wxpay/"+orderType; //调用微信下发通知的接口
        vo.put("notify_url", notifyUrl);
        vo.put("out_trade_no", orderNo);
        vo.put("spbill_create_ip", WenxinConfig.SPBILL_CREATE_IP);
        vo.put("total_fee", TotalMoney);
        vo.put("trade_type", "APP");
        //转成sortedMap,微信支付后台生成sign需要排序过的map
        SortedMap<String,Object> sort=new TreeMap<String,Object>(vo);
        String secrit = WenxinConfig.SECRIT;
        //生成sign
        String sign=WeixinSignUtil.createSign("UTF-8", sort,secrit);
        //把sign放入map中
        vo.put("sign", sign);
        Document doc = DocumentHelper.createDocument();
        Element body = DocumentHelper.createElement("xml");
        //XmlUtil.buildMap2xmlBody(body,vo);
        doc.add(body);
        //发送请求
        out.writeUTF(doc.asXML());
        System.out.println(doc.asXML());
        out.flush();
        out.close();

        //接收数据
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String line;
        StringBuffer responseText = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            responseText.append(line).append("\r\n");
        }
        reader.close();
        con.disconnect();
        return responseText.toString()+"";
    }


}
