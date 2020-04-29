package com.study.majinhu.designModel.Builder;

/**
 * @ClassName Coke
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 10:00
 * @Version 1.0
 **/
public class Coke extends ColdDrink {

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}