package com.study.majinhu.designModel.Builder;

/**
 * @ClassName ColdDrink
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:58
 * @Version 1.0
 **/
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
