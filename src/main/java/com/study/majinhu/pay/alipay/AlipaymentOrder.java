package com.study.majinhu.pay.alipay;

import java.util.Date;

/**
 * @ClassName AlipaymentOrder
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 15:34
 * @Version 1.0
 **/
public class AlipaymentOrder {
    private String appId ;//支付宝分配给开发者的应用Id
    private Date notifyTime ;//通知时间:yyyy-MM-dd HH:mm:ss
    private Date gmtCreate;//交易创建时间:yyyy-MM-dd HH:mm:ss
    private Date gmtPayment ;//交易付款时间
    private Date gmtRefund ;//交易退款时间
    private Date gmtClose;//交易结束时间
    private String tradeNo ;//支付宝的交易号
    private String outTradeNo ;//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
    private String outBizNo ;//商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
    private String buyerLogonId ;//买家支付宝账号
    private String sellerId ;//卖家支付宝用户号
    private String sellerEmail;//卖家支付宝账号
    private Double totalAmount ;//订单金额:本次交易支付的订单金额，单位为人民币（元）
    private Double receiptAmount;//实收金额:商家在交易中实际收到的款项，单位为元
    private Double invoiceAmount ;//开票金额:用户在交易中支付的可开发票的金额
    private Double buyerPayAmount ;//付款金额:用户在交易中支付的金额
    private Byte tradeStatus ;// 获取交易状态

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public Date getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(Date gmtClose) {
        this.gmtClose = gmtClose;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Double getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(Double buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public Byte getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Byte tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
