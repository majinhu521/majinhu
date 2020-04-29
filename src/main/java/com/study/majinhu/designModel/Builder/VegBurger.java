package com.study.majinhu.designModel.Builder;

/**
 * @ClassName VegBurger
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:59
 * @Version 1.0
 **/
public class VegBurger extends Burger {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}
