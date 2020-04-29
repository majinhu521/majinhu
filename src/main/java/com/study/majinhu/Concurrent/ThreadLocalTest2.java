package com.study.majinhu.Concurrent;

/**
 * @ClassName ThreadLocalTest2
 * @Description 同一个 ThreadLocal 变量在父线程中设置值后，在子线程中是获取不到的。
 * 为子线程调用 get 方法时候当前线程为子线程，而调用 set 方法设置线程变量是 main 线程，
 * 两者是不同的线程，自然子线程访问时候返回 null，那么有办法让子线程访问到父线程中的值吗？答案是有
 * InheritableThreadLocal 原理：
 * InheritableThreadLocal 的世界里，线程中的变量 inheritableThreadLocals 替代了 threadLocals。
 * 当父线程创建子线程时候，构造函数里面会把父线程中 inheritableThreadLocals 变量里面的
 * 本地变量拷贝一份复制到子线程的 inheritableThreadLocals 变量里面。
 * @Author majinhu
 * @Date 2019/6/19 10:59
 * @Version 1.0
 **/
public class ThreadLocalTest2 {
    //public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    //(1) 创建线程变量
    public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();
    public static void main(String[] args) {
        //(2)  设置线程变量
        threadLocal.set("hello world");
        //(3) 启动子线程
        Thread thread = new Thread(new  Runnable() {
            public void run() {
                //(4)子线程输出线程变量的值
                System.out.println("thread:" + threadLocal.get());
            }
        });
        thread.start();
        //(5)主线程输出线程变量值
        System.out.println("main:" + threadLocal.get());

    }
}
