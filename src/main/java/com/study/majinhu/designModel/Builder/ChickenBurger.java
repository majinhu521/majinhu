package com.study.majinhu.designModel.Builder;

/**
 * @ClassName ChickenBurger
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:59
 * @Version 1.0
 **/
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}
