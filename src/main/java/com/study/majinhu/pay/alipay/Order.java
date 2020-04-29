package com.study.majinhu.pay.alipay;

import com.alipay.api.domain.*;

/**
 * @ClassName Order
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 14:53
 * @Version 1.0
 **/
public class Order {
    //对一笔交易的具体描述信息
    private String body;

    private String businessParams;

    private String disablePayChannels;

    private String enablePayChannels;

    private ExtUserInfo extUserInfo;

    private ExtendParams extendParams;

    private String goodsType;

    private InvoiceInfo invoiceInfo;

    //商户订单号(自动生成)
    private String outTradeNo;

    private String passbackParams;

    //销售产品码（固定值）
    private String productCode;

    private String promoParams;

    private RoyaltyInfo royaltyInfo;

    private String sellerId;

    private SettleInfo settleInfo;

    private String specifiedChannel;

    private String storeId;

    private SubMerchant subMerchant;

    //商品名称
    private String subject;

    private String timeExpire;

    //交易超时时间
    private String timeoutExpress;

    //支付金额
    private String totalAmount;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBusinessParams() {
        return businessParams;
    }

    public void setBusinessParams(String businessParams) {
        this.businessParams = businessParams;
    }

    public String getDisablePayChannels() {
        return disablePayChannels;
    }

    public void setDisablePayChannels(String disablePayChannels) {
        this.disablePayChannels = disablePayChannels;
    }

    public String getEnablePayChannels() {
        return enablePayChannels;
    }

    public void setEnablePayChannels(String enablePayChannels) {
        this.enablePayChannels = enablePayChannels;
    }

    public ExtUserInfo getExtUserInfo() {
        return extUserInfo;
    }

    public void setExtUserInfo(ExtUserInfo extUserInfo) {
        this.extUserInfo = extUserInfo;
    }

    public ExtendParams getExtendParams() {
        return extendParams;
    }

    public void setExtendParams(ExtendParams extendParams) {
        this.extendParams = extendParams;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPassbackParams() {
        return passbackParams;
    }

    public void setPassbackParams(String passbackParams) {
        this.passbackParams = passbackParams;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPromoParams() {
        return promoParams;
    }

    public void setPromoParams(String promoParams) {
        this.promoParams = promoParams;
    }

    public RoyaltyInfo getRoyaltyInfo() {
        return royaltyInfo;
    }

    public void setRoyaltyInfo(RoyaltyInfo royaltyInfo) {
        this.royaltyInfo = royaltyInfo;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public SettleInfo getSettleInfo() {
        return settleInfo;
    }

    public void setSettleInfo(SettleInfo settleInfo) {
        this.settleInfo = settleInfo;
    }

    public String getSpecifiedChannel() {
        return specifiedChannel;
    }

    public void setSpecifiedChannel(String specifiedChannel) {
        this.specifiedChannel = specifiedChannel;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public SubMerchant getSubMerchant() {
        return subMerchant;
    }

    public void setSubMerchant(SubMerchant subMerchant) {
        this.subMerchant = subMerchant;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
