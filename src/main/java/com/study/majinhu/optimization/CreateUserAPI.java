package com.study.majinhu.optimization;

import lombok.Data;

/**
 * @ClassName CreateUserAPI
 * @Description
 * @Author majinhu
 * @Date 2021/3/31 16:13
 * @Version 1.0
 **/
@Data
@BankAPI(url="/bank/createUser",desc="创建用户接口")
public class CreateUserAPI extends AbstractAPI {
    @BankAPIField(order=1,length=10,type="S")
    private String name;
    @BankAPIField(order=2,length=18,type="S")
    private String identity;
    @BankAPIField(order=4,length=11,type="S")
    private String mobile;
    @BankAPIField(order=3,length=5,type="N")
    private int age;
}