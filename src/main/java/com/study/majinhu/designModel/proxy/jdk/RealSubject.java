package com.study.majinhu.designModel.proxy.jdk;

/**
 * @ClassName RealSubject
 * @Description 真实对象
 * @Author majinhu
 * @Date 2019/6/21 9:39
 * @Version 1.0
 **/
public class RealSubject implements Subject{
    @Override
    public int sellBooks() {
        System.out.println("卖书");
        return 1 ;
    }

    @Override
    public String speak() {
        System.out.println("说话");
        return "张三";
    }
}
