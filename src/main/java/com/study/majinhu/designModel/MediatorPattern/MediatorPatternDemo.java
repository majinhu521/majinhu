package com.study.majinhu.designModel.MediatorPattern;

/**
 * @ClassName MediatorPatternDemo
 * @Description 中介者模式
 * @Author majinhu
 * @Date 2020/5/18 10:20
 * @Version 1.0
 **/
public class MediatorPatternDemo {
    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}
