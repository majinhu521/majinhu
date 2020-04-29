package com.study.majinhu.dubbo.java_spi;

/**
 * @ClassName OptimusPrime
 * @Description
 * @Author majinhu
 * @Date 2019/12/31 16:06
 * @Version 1.0
 **/
public class OptimusPrime implements Robot{
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Optimus Prime.");
    }
}
