package com.study.majinhu.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.study.majinhu.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName AlipayService
 * @Description
 * 1.用户点击"立即购买”时调用商户后台接口，后台返回加签后的订单信息字符串
 *
 * 2.在支付完成之后，支付宝异步通知商户后台订单的付款情况
 *
 * 3.在支付完成之后，跳转回APP时，APP调用商户后台进行最终付款校验
 * @Author majinhu
 * @Date 2019/8/15 14:45
 * @Version 1.0
 **/
@Controller
public class AlipayController {
    private static Logger logger = LoggerFactory.getLogger(AlipayController.class);

    /**
     * 获取支付宝加签后台的订单信息字符串
     * https://search.maven.org/search?q=g:com.alipay.sdk%20AND%20a:alipay-sdk-java&core=gav
     * https://docs.open.alipay.com/54/106370/
     * 1.用户点击"立即购买”时调用商户后台接口，后台返回加签后的订单信息字符串
     *      *  https://docs.open.alipay.com/204/105465/
     * @param
     * @return
     */
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    public String getAliPayOrderStr(Order order) {

        //最终返回加签之后的，app需要传给支付宝app的订单信息字符串
        String orderString = "";
        logger.info("==================支付宝下单,商户订单号为：" + order.getOutTradeNo());

        //创建商户支付宝订单(因为需要记录每次支付宝支付的记录信息，单独存一个表跟商户订单表关联，以便以后查证)
//        AlipaymentOrder alipaymentOrder = new AlipaymentOrder();
//        alipaymentOrder.setClubOrderId(orderTest.getId().toString());//商家订单主键
//        alipaymentOrder.setOutTradeNo(orderTest.getOutTradeNo());//商户订单号
//        alipaymentOrder.setTradeStatus((byte) 0);//交易状态
//        alipaymentOrder.setTotalAmount(Double.parseDouble(order.getTotalAmount()));//订单金额
//        alipaymentOrder.setReceiptAmount(0.00);//实收金额
//        alipaymentOrder.setInvoiceAmount(0.00);//开票金额
//        alipaymentOrder.setBuyerPayAmount(0.00);//付款金额
//        alipaymentOrder.setRefundFee(0.00);    //总退款金额

        try {
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID,
                    AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                    AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();

            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

            //业务参数传入,可以传很多，参考API     https://docs.open.alipay.com/204/105465/
            //model.setPassbackParams(URLEncoder.encode(request.getBody().toString())); //公用参数（附加数据）
            model.setBody(order.getBody());                       //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
            model.setSubject(order.getSubject());                 //商品名称
            model.setOutTradeNo(order.getOutTradeNo());           //商户订单号(自动生成)
            model.setTimeoutExpress("30m");     			  //交易超时时间
            model.setTotalAmount(order.getTotalAmount());         //支付金额
            model.setProductCode("QUICK_MSECURITY_PAY");              //销售产品码（固定值）
            ali_request.setBizModel(model);
            ali_request.setNotifyUrl(AlipayConfig.NOTIFY_URL);        //异步回调地址（后台）
            ali_request.setReturnUrl(AlipayConfig.RETURN_URL);        //同步回调地址（APP）

            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse alipayTradeAppPayResponse = alipayClient.sdkExecute(ali_request); //返回支付宝订单信息(预处理)
            orderString = alipayTradeAppPayResponse.getBody();//就是orderString 可以直接给APP请求，无需再做处理。

            //this.createAlipayMentOrder(alipaymentOrder);//创建新的商户支付宝订单

        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.info("与支付宝交互出错，未能生成订单，请检查代码！");
        }

        return orderString;
    }

//    /**
//     * @Author majinhu
//     * @Description
//     * 1.用户点击"立即购买”时调用商户后台接口，后台返回加签后的订单信息字符串
//     *      *  https://docs.open.alipay.com/204/105465/
//     * @Date 15:23 2019/8/15
//     * @Param []
//     * @return java.lang.String
//     **/
//    public String createAlipayMentOrder(){
//        //实例化客户端
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID,
//                AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
//                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
//        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
//        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//        String orderString ="";
//        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
//        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//        model.setBody("我是测试数据");  //对一笔交易的具体描述信息。
//        model.setSubject("App支付测试Java");  //商品名称
//        model.setOutTradeNo("outtradeno");
//        model.setTimeoutExpress("30m");
//        model.setTotalAmount("0.01");
//        model.setProductCode("QUICK_MSECURITY_PAY");
//        request.setBizModel(model);
//        request.setNotifyUrl("商户外网可以访问的异步地址");
//        try {
//            //这里和普通的接口调用不同，使用的是sdkExecute
//            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
//            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
//            orderString = response.getBody();
//
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        return orderString;
//    }

    /**
     * 支付宝支付成功后.异步请求该接口
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/notify_url",method= RequestMethod.POST)
    @ResponseBody
    public String notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("==================支付宝异步返回支付结果开始");
        //1.从支付宝回调的request域中取值
        //获取支付宝返回的参数集合
        Map<String, String[]> aliParams = request.getParameterMap();
        //用以存放转化后的参数集合
        Map<String, String> conversionParams = new HashMap<String, String>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext(); ) {
            String key = iter.next();
            String[] values = aliParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "uft-8");
            conversionParams.put(key, valueStr);
        }
        logger.info("==================返回参数集合：" + conversionParams);
        String status = this.notify(conversionParams);
        return status;
    }



        /**
         * 支付宝异步请求逻辑处理
         * @param conversionParams
         * @return
         * @throws IOException
         */
        public String notify(Map<String, String> conversionParams){

            logger.info("==================支付宝异步请求逻辑处理");

            //签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
            boolean signVerified = false;

            try {
                //调用SDK验证签名
                signVerified = AlipaySignature.rsaCheckV1(conversionParams, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);

            } catch (AlipayApiException e) {
                logger.info("==================验签失败 ！");
                e.printStackTrace();
            }

            //对验签进行处理
            if (signVerified) {
                //验签通过
                //获取需要保存的数据
                String appId = conversionParams.get("app_id");//支付宝分配给开发者的应用Id
                String notifyTime = conversionParams.get("notify_time");//通知时间:yyyy-MM-dd HH:mm:ss
                String gmtCreate = conversionParams.get("gmt_create");//交易创建时间:yyyy-MM-dd HH:mm:ss
                String gmtPayment = conversionParams.get("gmt_payment");//交易付款时间
                String gmtRefund = conversionParams.get("gmt_refund");//交易退款时间
                String gmtClose = conversionParams.get("gmt_close");//交易结束时间
                String tradeNo = conversionParams.get("trade_no");//支付宝的交易号
                String outTradeNo = conversionParams.get("out_trade_no");//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
                String outBizNo = conversionParams.get("out_biz_no");//商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
                String buyerLogonId = conversionParams.get("buyer_logon_id");//买家支付宝账号
                String sellerId = conversionParams.get("seller_id");//卖家支付宝用户号
                String sellerEmail = conversionParams.get("seller_email");//卖家支付宝账号
                String totalAmount = conversionParams.get("total_amount");//订单金额:本次交易支付的订单金额，单位为人民币（元）
                String receiptAmount = conversionParams.get("receipt_amount");//实收金额:商家在交易中实际收到的款项，单位为元
                String invoiceAmount = conversionParams.get("invoice_amount");//开票金额:用户在交易中支付的可开发票的金额
                String buyerPayAmount = conversionParams.get("buyer_pay_amount");//付款金额:用户在交易中支付的金额
                String tradeStatus = conversionParams.get("trade_status");// 获取交易状态

                //支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）
                AlipaymentOrder alipaymentOrder = new AlipaymentOrder();
                //AlipaymentOrder alipaymentOrder = this.selectByOutTradeNo(outTradeNo);

                if (alipaymentOrder != null && totalAmount.equals(alipaymentOrder.getTotalAmount().toString()) && AlipayConfig.APP_ID.equals(appId)) {
                    //修改数据库支付宝订单表(因为要保存每次支付宝返回的信息到数据库里，以便以后查证)
                    alipaymentOrder.setNotifyTime(DateUtil.fomatDate(notifyTime));
                    alipaymentOrder.setGmtCreate(DateUtil.fomatDate(gmtCreate));
                    alipaymentOrder.setGmtPayment(DateUtil.fomatDate(gmtPayment));
                    alipaymentOrder.setGmtRefund(DateUtil.fomatDate(gmtRefund));
                    alipaymentOrder.setGmtClose(DateUtil.fomatDate(gmtClose));
                    alipaymentOrder.setTradeNo(tradeNo);
                    alipaymentOrder.setOutBizNo(outBizNo);
                    alipaymentOrder.setBuyerLogonId(buyerLogonId);
                    alipaymentOrder.setSellerId(sellerId);
                    alipaymentOrder.setSellerEmail(sellerEmail);
                    alipaymentOrder.setTotalAmount(Double.parseDouble(totalAmount));
                    alipaymentOrder.setReceiptAmount(Double.parseDouble(receiptAmount));
                    alipaymentOrder.setInvoiceAmount(Double.parseDouble(invoiceAmount));
                    alipaymentOrder.setBuyerPayAmount(Double.parseDouble(buyerPayAmount));
                    switch (tradeStatus) // 判断交易结果
                    {
                        case "TRADE_FINISHED": // 交易结束并不可退款
                            alipaymentOrder.setTradeStatus((byte) 3);
                            break;
                        case "TRADE_SUCCESS": // 交易支付成功
                            alipaymentOrder.setTradeStatus((byte) 2);
                            break;
                        case "TRADE_CLOSED": // 未付款交易超时关闭或支付完成后全额退款
                            alipaymentOrder.setTradeStatus((byte) 1);
                            break;
                        case "WAIT_BUYER_PAY": // 交易创建并等待买家付款
                            alipaymentOrder.setTradeStatus((byte) 0);
                            break;
                        default:
                            break;
                    }
                    int returnResult = 1;    //更新交易表中状态
                    //int returnResult = this.updateByPrimaryKey(alipaymentOrder);    //更新交易表中状态

                    if (tradeStatus.equals("TRADE_SUCCESS")) {    //只处理支付成功的订单: 修改交易表状态,支付成功

                        if (returnResult > 0) {
                            return "success";
                        } else {
                            return "fail";
                        }
                    } else {
                        return "fail";
                    }

                } else {
                    logger.info("==================支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）,不一致！返回fail");
                    return "fail";
                }

            } else {  //验签不通过
                logger.info("==================验签不通过 ！");
                return "fail";
            }
        }


    /**
     * 向支付宝发起订单查询请求
     * @param
     * @return
     * @throws IOException
     */
    public Byte checkAlipay(String outTradeNo) {
        logger.info("==================向支付宝发起查询，查询商户订单号为：" + outTradeNo);

        try {
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型）
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID,
                    AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                    AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
            AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
            alipayTradeQueryRequest.setBizContent("{" +
                    "\"out_trade_no\":\"" + outTradeNo + "\"" +
                    "}");
            AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.execute(alipayTradeQueryRequest);
            if (alipayTradeQueryResponse.isSuccess()) {
                AlipaymentOrder alipaymentOrder = new AlipaymentOrder();
                //AlipaymentOrder alipaymentOrder = this.selectByOutTradeNo(outTradeNo);
                //修改数据库支付宝订单表
                alipaymentOrder.setTradeNo(alipayTradeQueryResponse.getTradeNo());
                alipaymentOrder.setBuyerLogonId(alipayTradeQueryResponse.getBuyerLogonId());
                alipaymentOrder.setTotalAmount(Double.parseDouble(alipayTradeQueryResponse.getTotalAmount()));
                alipaymentOrder.setReceiptAmount(Double.parseDouble(alipayTradeQueryResponse.getReceiptAmount()));
                alipaymentOrder.setInvoiceAmount(Double.parseDouble(alipayTradeQueryResponse.getInvoiceAmount()));
                alipaymentOrder.setBuyerPayAmount(Double.parseDouble(alipayTradeQueryResponse.getBuyerPayAmount()));
                switch (alipayTradeQueryResponse.getTradeStatus()) // 判断交易结果
                {
                    case "TRADE_FINISHED": // 交易结束并不可退款
                        alipaymentOrder.setTradeStatus((byte) 3);
                        break;
                    case "TRADE_SUCCESS": // 交易支付成功
                        alipaymentOrder.setTradeStatus((byte) 2);
                        break;
                    case "TRADE_CLOSED": // 未付款交易超时关闭或支付完成后全额退款
                        alipaymentOrder.setTradeStatus((byte) 1);
                        break;
                    case "WAIT_BUYER_PAY": // 交易创建并等待买家付款
                        alipaymentOrder.setTradeStatus((byte) 0);
                        break;
                    default:
                        break;
                }
                //this.updateByPrimaryKey(alipaymentOrder); //更新表记录
                return alipaymentOrder.getTradeStatus();
            } else {
                logger.info("==================调用支付宝查询接口失败！");
            }
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;

    }

}



