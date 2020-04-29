package com.study.majinhu.designModel.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName CglibProxy
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
 * @Date 2019/6/21 10:14
 * @Version 1.0
 **/
public class CglibProxy  implements MethodInterceptor {
    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("###   before invocation");
        Object result = method.invoke(target, objects);
        System.out.println("###   end invocation");
        return result;
    }

    public static Object getProxy(Object target) {
        Enhancer enhancer = new Enhancer();
        // 设置需要代理的对象
        enhancer.setSuperclass(target.getClass());
        // 设置代理人
        enhancer.setCallback(new CglibProxy(target));
        return enhancer.create();
    }
}