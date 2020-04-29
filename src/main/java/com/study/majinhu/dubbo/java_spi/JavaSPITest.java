package com.study.majinhu.dubbo.java_spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @ClassName JavaSPITest
 * @Description
 * @Author majinhu
 * @Date 2019/12/31 16:08
 * @Version 1.0
 **/
public class JavaSPITest {

    @Test
    public void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}