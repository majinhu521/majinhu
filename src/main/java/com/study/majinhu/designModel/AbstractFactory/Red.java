package com.study.majinhu.designModel.AbstractFactory;

/**
 * @ClassName Red
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:27
 * @Version 1.0
 **/
public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}