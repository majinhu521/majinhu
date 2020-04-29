package com.study.majinhu.designModel.proxy.wangmeili;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName WangMeiLiFamily
 * @Description 代理模式-代理类
 * @Author majinhu
 * @Date 2019/7/25 8:57
 * @Version 1.0
 **/
public class WangMeiLiFamily implements InvocationHandler {
    //接口需要作为成员变量引入
    private Girl girl;
    //构造函数，需要把Girl包含进来。
    public WangMeiLiFamily(Girl girl){
        super();
        this.girl = girl;
    }
   //InvocationHandler 的 invoke方法进行 方法增强。
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();
        Object invoke = method.invoke(girl, args);
        doSomethingAfter();
        return invoke;
    }
    private void doSomethingBefore(){
        System.out.println("小伙子怎么样，多少钱一个月，有车，有房吗？");
    }
    private void doSomethingAfter(){
        System.out.println("小伙子对你怎么样，有没有动手动脚？");
    }
    //获取代理的实例，把类，接口，代理类进行连接起来。
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(girl.getClass().getClassLoader(),girl.getClass().getInterfaces(),this);
    }
}
