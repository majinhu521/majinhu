package com.study.majinhu.dubbo.java_spi;

/**
 * @ClassName Bumblebee
 * @Description
 * @Author majinhu
 * @Date 2019/12/31 16:07
 * @Version 1.0
 **/
public class Bumblebee implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}