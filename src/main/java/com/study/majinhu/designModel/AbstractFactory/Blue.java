package com.study.majinhu.designModel.AbstractFactory;

/**
 * @ClassName Blue
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:28
 * @Version 1.0
 **/
public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}