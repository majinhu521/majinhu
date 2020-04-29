package com.study.majinhu.designModel.proxy.cglib;

/**
 * @ClassName CglibMainTest
 * @Description 其他：https://blog.csdn.net/wangzhihao1994/article/details/80913210
 * 浅谈Spring中JDK动态代理与CGLIB动态代理
 * @Author majinhu
 * @Date 2019/6/21 10:15
 * @Version 1.0
 **/
public class CglibMainTest {
    public static void main(String[] args) {
        // 生成 Cglib 代理类
        Engineer engineerProxy = (Engineer) CglibProxy.getProxy(new Engineer());
        // 调用相关方法
        engineerProxy.eat();
        engineerProxy.work();
    }
}