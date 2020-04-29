package com.study.majinhu.designModel.AbstractFactory;

/**
 * @ClassName Green
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:27
 * @Version 1.0
 **/
public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
