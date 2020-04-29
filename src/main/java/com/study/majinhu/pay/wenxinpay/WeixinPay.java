package com.study.majinhu.pay.wenxinpay;

import com.study.majinhu.httpClient.JsonUtil;
import org.apache.http.client.ResponseHandler;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.Element;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * @ClassName WeixinPay
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 16:46
 * @Version 1.0
 **/
//public class WeixinPay {
//
//    /**获取微信客户端支付信息 如 签名 随机数等
//     * @param page
//     * @throws Exception
//     */
//    @RequestMapping(value="/getWXClientPayInfo")
//    @ResponseBody
//    public String getWeixinPayInfo(HttpServletRequest request) throws Exception{
//        String orderNumber = request.getParameter("outTradeNo");
//        String orderType = request.getParameter("orderType");
//        Map<String, Object> map=new HashMap<String, Object>();
//        map.put("orderNumber", orderNumber);
//        map.put("type", orderType);
//        //获取订单信息
//        Map<String, Object> orderMap=travelOrderService.getOrderInfoByOrderType(map);
//        String amount="";
//        String prepayId="";
//        if(null!=orderMap&&orderMap.size()>0){
//            if(orderMap.containsKey("amount")){
//                amount=orderMap.get("amount").toString();
//            }
//            if(orderMap.containsKey("prepayId")){
//                prepayId=orderMap.get("prepayId").toString();
//            }
//        }
//        //获取微信支付会话id
//        if("".equals(prepayId)){
//            Float totalMoney=Float.valueOf(amount);
//            totalMoney=totalMoney*100;
//            int totalFee=totalMoney.intValue();
//            String money=String.valueOf(totalFee);
//            String xmlStr= WeixinPayUtil.add(orderNumber,money,orderType); //获得会话ID</span>
//            Map<String, Object> m=new HashMap<String, Object>();
//            Document doc = DocumentHelper.parseText(xmlStr);
//            org.dom4j.Element rootElement = doc.getRootElement();
//            XmlToBean.ele2map(m,rootElement);
//            String str="";
//            if(m.containsKey("prepay_id")){
//                str=m.get("prepay_id").toString();
//                str=str.replace("{prepay_id=", "");
//                str=str.substring(0,str.length()-1);
//            }
//            prepayId=str;
//            map.put("prepayId", prepayId);
//            travelOrderService.updateOrderInfoByType(map);
//        }
//        //转成sortedMap,微信支付后台生成sign需要排序过的map
//        SortedMap<String,Object> sort=new TreeMap<String,Object>();
//        String partnerid = sysparam.getStringParamByKey("c.wxpay.partnerid");
//        //相关参数,可以定义一个Map做成动态的
//        sort.put("appid", sysparam.getStringParamByKey("c.wxpay.appid"));
//        sort.put("partnerid", partnerid);
//        sort.put("prepayid",prepayId);
//        sort.put("package", "Sign=WXPay");
//        String noncestr = RandomUtil.CreateRandom(32);
//        sort.put("noncestr", noncestr);
//        String timestamp = String.valueOf(System.currentTimeMillis());
//        timestamp = timestamp.substring(0,timestamp.length()-3);
//        sort.put("timestamp", timestamp);
//        String securit = sysparam.getStringParamByKey("c.wxpay.secret");
//        //生成sign
//        String sign=WeixinSignUtil.createSign("UTF-8", sort,securit);
//
//        Map<String,Object> resultParam = new HashMap<String, Object>();
//        resultParam.put("timestamp", timestamp);
//        resultParam.put("noncestr", noncestr);
//        resultParam.put("sign", sign);
//        resultParam.put("prepayid", prepayId);
//        return JsonUtil.obj2Json(resultParam);
//    }
//
//    @RequestMapping(value = "/interface/wxpay/{orderType}", produces = "text/html;charset=UTF-8")
//    @ResponseBody
//    public String getWeixinPayResult(HttpServletRequest request, HttpServletResponse response)
//            throws IOException
//    {
//        try
//        {
//            String reqcontent = getRequestContent(request);
//            interfaceLogger.info("################### ################# WeiXinPayResultInterface::getWeixinPayResult,request msg:"
//                    + reqcontent);
//            ResponseHandler resHandler = new ResponseHandler(request, response);
//
//            // 创建请求对象
//
//            // RequestHandler queryReq = new RequestHandler(request, response);
//            String type = request.getParameter("orderType"); // 获取订单类型
//
//            // queryReq.init();
//            String securit = sysParam.getStringParamByKey("c.wxpay.secret");
//
//            if (resHandler.getParameter("sign").equals(
//                    WeixinSignUtil.createSign("utf-8", resHandler.getAllParameters(), securit)))
//            {
//
//                // 商户订单号
//                String out_trade_no = resHandler.getParameter("out_trade_no");
//                String totalFee = resHandler.getParameter("total_fee");
//                System.out.println("out_trade_no:" + out_trade_no);
//                Map<String, Object> map = new HashMap<String, Object>();
//                String result_code = resHandler.getParameter("result_code");
//                if (result_code.equals("SUCCESS"))
//                {
//                    // 金额,以分为单位</span>
//                    String total_fee = resHandler.getParameter("total_fee");
//                    //进行支付后操作...
//                } else {
//                    return resHandler.getParameter("err_code_des"); }
//                // 给微信服务器返回success 否则30分钟通知8次 return "success";
//                //
//            } else {
//                System.out.println("通知签名验证失败");
//                resHandler.sendToCFT("fail"); response.setCharacterEncoding("utf-8"); return "FAIL"; } }
//        catch (Exception e) { return "FAIL"; } }
//
//
//
//
//    /**
//     * 客户端微信支付成功后查询后台支付结果接口
//     *
//     * @param req
//     * @param rsp
//     * @return String 返回json字符串 如果有违例，请使用@exception/throws [违例类型]
//     *         [违例说明：异常的注释必须说明该异常的含义及什么条件下抛出该
//     * @throws Exception
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping(value = "/interface/payStatus", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
//    @ResponseBody
//    public String getPayStatus(HttpServletRequest req, HttpServletResponse rsp)
//            throws Exception
//    {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        try
//        {
//            URL postUrl = new URL("https://api.mch.weixin.qq.com/pay/orderquery");
//            // 获取请求内容
//            String requestContent = getRequestContent(req);
//            // 日志
//            interfaceLogger.info("request payStatus:" + requestContent);
//            // 解析参数
//            requestParam = parseAndCheckParam(requestContent, resultMap,
//                    requestParam, systemParam.getSystemKey());
//            // 校验参数
//            checkStatusParam(resultMap);
//            // 校验token及获取用户信息
//            Map<String, Object> userInfo = checkTokenAndGetUserInfo(
//                    tokenService, appuserManager, requestParam, resultMap);
//
//            String user_id = (String)userInfo.get("userId");
//            String totalFee = (String)requestParam.get("totalFee");
//            String type = (String)requestParam.get("orderType"); // 获取订单类型参数
//            if (null == user_id)
//            {
//                resultMap.put("resultCode",
//                        ErrorCodeConstants.INVALID_TOKEN_ERROR);
//            }
//            SysParamServiceImpl sysParam = (SysParamServiceImpl)SpringContextHolder
//                    .getBean(SysParamServiceImpl.class);
//            HttpsURLConnection con = (HttpsURLConnection)postUrl
//                    .openConnection();// 打开连接
//            con.setRequestMethod("POST");// post方式提交
//            con.setDoOutput(true);// 打开读写属性，默认均为false
//            con.setDoInput(true);
//            con.setUseCaches(false);// Post请求不能使用缓存
//            con.setInstanceFollowRedirects(true);
//            // DataOutputStream out = new
//            // DataOutputStream(con.getOutputStream());
//            PrintWriter out = new PrintWriter(con.getOutputStream());
//            Map<String, Object> map = new HashMap<String, Object>();
//            // 相关参数,可以定义一个Map做成动态的
//            map.put("appid", sysParam.getStringParamByKey("c.wxpay.appid")); // appID
//            map.put("mch_id", sysParam.getStringParamByKey("c.wxpay.partnerid")); // 商户号
//            map.put("nonce_str", RandomUtil.CreateRandom(32)); // 随机数
//            String orderId = requestParam.get("outTradeNo").toString();
//            map.put("out_trade_no", orderId); // 订单号
//            // String mo="3000";
//            // insertMoneyAccounting(orderId,mo); //利润分配
//            // 转成sortedMap,微信支付后台生成sign需要排序过的map
//            SortedMap<String, Object> sort = new TreeMap<String, Object>(map);
//            String secrit = sysParam.getStringParamByKey("c.wxpay.secret");
//            // 生成sign
//            String sign = WeixinSignUtil.createSign("UTF-8", sort, secrit);
//            // 把sign放入map中
//            map.put("sign", sign); // 签名
//            Document doc = DocumentHelper.createDocument();
//            Element body = DocumentHelper.createElement("xml");
//            buildMap2xmlBody(body, map);
//            doc.add(body);
//            // 发送请求
//            // out.writeUTF(doc.asXML());
//            String outStr = doc.asXML();
//            out.print(outStr);
//            System.out.println(doc.asXML());
//            out.flush();
//            out.close();
//            // 接收数据
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    con.getInputStream(), "UTF-8"));
//            String line;
//            StringBuffer responseText = new StringBuffer();
//            while ( (line = reader.readLine()) != null)
//            {
//                responseText.append(line).append("\r\n");
//            }
//            reader.close();
//            con.disconnect();
//            String resXml = responseText.toString() + ""; // 获取从微信付款的结果
//            // ，以String类型的xml格式返回
//            System.out.println("result:" + resXml);
//            // 解析xml
//            Map<String, Object> m = new HashMap<String, Object>();
//            Document d = DocumentHelper.parseText(resXml);
//            Element rootElement = d.getRootElement();
//            XmlToBean.ele2map(m, rootElement);
//            String str = "";
//            if (m.containsKey("trade_state"))
//            {
//                str = m.get("trade_state").toString();
//                str = str.replace("{trade_state=", "");
//                str = str.substring(0, str.length() - 1);
//                if ("SUCCESS" == str || "SUCCESS".equals(str))
//                {
//                    if ("0".equals(type)) {
//                        //支付成功后操作...
//                    }
//                }
//                else if ("REFUND" == str || "REFUND".equals(str))
//                {
//                    str = "1"; // 转入退款
//                }
//                else if ("NOTPAY" == str || "NOTPAY".equals(str))
//                {
//                    str = "2"; // 未支付
//                }
//                else if ("CLOSED" == str || "CLOSED".equals(str))
//                {
//                    str = "3"; // 已关闭
//                }
//                else if ("REVOKED" == str || "REVOKED".equals(str))
//                {
//                    str = "4"; // 已撤销
//                }
//                else if ("USERPAYING" == str || "USERPAYING".equals(str))
//                {
//                    str = "5"; // 用户支付中
//                }
//                else if ("PAYERROR" == str || "PAYERROR".equals(str))
//                {
//                    str = "6"; // 支付失败
//                }
//                resultMap.put("status", str);
//                resultMap.put("resultCode",
//                        ErrorCodeConstants.RESULT_CODE_SUCCESS);
//            }
//        }
//        catch (Exception e)
//        {
//            logger.error("loginOut:system exception!", e);
//            resultMap.clear();
//            resultMap.put("resultCode", ErrorCodeConstants.SYSTEM_ERROR);
//        }
//        interfaceLogger.info("response payStatus:" + resultMap.toString());
//        return JsonUtil.Object2EncodeJsonSting(resultMap,
//                systemParam.getSystemKey());
//    }
//}
