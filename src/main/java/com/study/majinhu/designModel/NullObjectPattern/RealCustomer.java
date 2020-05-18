package com.study.majinhu.designModel.NullObjectPattern;

/**
 * @ClassName RealCustomer
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:27
 * @Version 1.0
 **/
public class RealCustomer extends AbstractCustomer {

    public RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isNil() {
        return false;
    }
}