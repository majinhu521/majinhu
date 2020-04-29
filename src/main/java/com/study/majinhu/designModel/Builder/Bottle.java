package com.study.majinhu.designModel.Builder;

/**
 * @ClassName Bottle
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:57
 * @Version 1.0
 **/
public class Bottle implements Packing {

    @Override
    public String pack() {
        return "Bottle";
    }
}