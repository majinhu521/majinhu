package com.study.majinhu.designModel.Builder;

/**
 * @ClassName Burger
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:57
 * @Version 1.0
 **/
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}