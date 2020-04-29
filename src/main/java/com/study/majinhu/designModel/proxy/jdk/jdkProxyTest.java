package com.study.majinhu.designModel.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @ClassName jdkProxyTest
 * @Description jdk动态代理测试：获取真实对象，Proxy.newProxyInstance进行调用返回一个代理对象，然后调用代理对象的方法。
 * @Author majinhu
 * @Date 2019/6/21 9:44
 * @Version 1.0
 **/
public class jdkProxyTest {
    public static void main(String[] args) {
        //真实对象
        Subject realSubject =  new RealSubject();

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        //代理对象
        Subject proxyClass = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, myInvocationHandler);

        proxyClass.sellBooks();

        proxyClass.speak();
    }
}
