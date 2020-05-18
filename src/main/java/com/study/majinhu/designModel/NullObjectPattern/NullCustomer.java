package com.study.majinhu.designModel.NullObjectPattern;

/**
 * @ClassName NullCustomer
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:27
 * @Version 1.0
 **/
public class NullCustomer extends AbstractCustomer {

    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }

    @Override
    public boolean isNil() {
        return true;
    }
}