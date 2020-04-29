package com.study.majinhu.designModel.Builder;

/**
 * @ClassName Pepsi
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 10:00
 * @Version 1.0
 **/
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}
