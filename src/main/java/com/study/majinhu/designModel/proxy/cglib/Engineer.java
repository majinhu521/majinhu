package com.study.majinhu.designModel.proxy.cglib;

/**
 * @ClassName Engineer
 * @Description
 * Cglib 动态代理是针对代理的类, 动态生成一个子类, 然后子类覆盖代理类中的方法,
 * 如果是private或是final类修饰的方法,则不会被重写。
 *
 * CGLIB是一个功能强大，高性能的代码生成包。它为没有实现接口的类提供代理，
 * 为JDK的动态代理提供了很好的补充。通常可以使用Java的动态代理创建代理，
 * 但当要代理的类没有实现接口或者为了更好的性能，CGLIB是一个好的选择。
 *
 * CGLIB作为一个开源项目，其代码托管在github，地址为：https://github.com/cglib/cglib
 * @Author majinhu
 * @Date 2019/6/21 10:13
 * @Version 1.0
 **/
public class Engineer {
    // 可以被代理
    public void eat() {
        System.out.println("工程师正在吃饭");
        work2();
        //获取代理对象，让代理对象去执行work2();

    }
    public  void work2() {
        System.out.println("工程师边吃饭边工作");
    }

    // final 方法不会被生成的字类覆盖
    public final void work() {
        System.out.println("工程师正在工作");
    }

    // private 方法不会被生成的字类覆盖
    private void play() {
        System.out.println("this engineer is playing game");
    }
}