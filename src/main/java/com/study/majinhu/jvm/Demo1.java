package com.study.majinhu.jvm;

/**
 * @ClassName Demo1
 * @Description 内加载机制：
 * 双亲委派模型
 * 1. bootstrap ClassLoader  === sun.launcher  jre/lib/rt.jar
 * 2. Extension ClassLoader   sun.launcher$ExtClassLoader
 * 3. App  ClassLoader
 * @Author majinhu
 * @Date 2020/4/29 10:03
 * @Version 1.0
 **/
public class Demo1 {
    public static void main(String[] args) {
        System.out.println(Demo1.class.getClassLoader()); //APP
        System.out.println(Demo1.class.getClassLoader().getParent()); //Extension
        System.out.println(Demo1.class.getClassLoader().getParent().getParent());//bootstrap
        System.out.println(new String().getClass().getClassLoader());
    }
}
