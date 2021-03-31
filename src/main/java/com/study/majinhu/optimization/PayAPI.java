package com.study.majinhu.optimization;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName CreateUserAPI
 * @Description
 * @Author majinhu
 * @Date 2021/3/31 16:13
 * @Version 1.0
 **/
@Data
@BankAPI(url="/bank/Pay",desc="支付接口")
public class PayAPI extends AbstractAPI {
    @BankAPIField(order=1,length=20,type="S")
    private Long USERID;
    @BankAPIField(order=2,length=10,type="M")
    private BigDecimal amount;
}