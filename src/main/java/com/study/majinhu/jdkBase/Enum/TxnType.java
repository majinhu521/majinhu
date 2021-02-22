package com.study.majinhu.jdkBase.Enum;

/**
 * @ClassName TxnType
 * @Description 交易类型
 * @Author HZY
 * @Date 2019/12/13 10:41
 * @Version 1.0
 **/
public enum TxnType {

    RECHARGE("01","充值"),
    PAYMENT("02","乘车消费"),
    REFUND("04","退款"),
    CASH_OUT("05","提现");

    private String code;
    private String message;

    TxnType(String code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public String getCode()
    {
        return this.code;
    }

    public String getMessage()
    {
        return this.message;
    }

}