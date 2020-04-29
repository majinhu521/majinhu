package com.study.majinhu.Concurrent;

/**
 * @ClassName ThreadLocalTest
 * @Description
 * 开启了两个线程，每个线程内部设置了本地变量的值，
 * 然后调用 print 函数打印当前本地变量的值，
 * 如果打印后调用了本地变量的 remove 方法则会删除本地内存中的该变量
 * @Author majinhu
 * @Date 2019/6/19 10:45
 * @Version 1.0
 **/
public class ThreadLocalTest {
    //(1)打印函数
    static void print(String str) {
        //1.1  打印当前线程本地内存中localVariable变量的值
        System.out.println(str + ":" + localVariable.get());
        //1.2 清除当前线程本地内存中localVariable变量
        localVariable.remove();
    }
    //(2) 创建ThreadLocal变量
    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {
        //(3) 创建线程one
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                //3.1 设置线程one中本地变量localVariable的值
                localVariable.set("threadOne local variable");
                //3.2 调用打印函数
                print("threadOne");
                //3.3打印本地变量值
                System.out.println("threadOne remove after" + ":" + localVariable.get());

            }
        });
        //(4) 创建线程two
        Thread threadTwo = new Thread(new Runnable() {
            public void run() {
                //4.1 设置线程one中本地变量localVariable的值
                localVariable.set("threadTwo local variable");
                //4.2 调用打印函数
                print("threadTwo");
                //4.3打印本地变量值
                System.out.println("threadTwo remove after" + ":" + localVariable.get());

            }
        });
        //(5)启动线程
        threadOne.start();
        threadTwo.start();
    }



}