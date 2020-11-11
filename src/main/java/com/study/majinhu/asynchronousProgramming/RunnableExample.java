package com.study.majinhu.asynchronousProgramming;

/**
 * @ClassName RunnableExample
 * @Description Runnable 多线程异步示例
 *  doSomething b
 *  doSomething a
 * 2105
 * @Author majinhu
 * @Date 2020/11/11 9:15
 * @Version 1.0
 **/
public class RunnableExample {

    public static void doSomethingA() {
        try {
            Thread.sleep(2000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" doSomething a ");
    }

    public static void doSomethingB() {
        try {
            Thread.sleep(1000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" doSomething b ");
    }
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        //1. 执行任务
        //doSomethingA() ;
        Thread thread = new Thread(()->{
            doSomethingA() ;
        },"thread a");
        thread.start();
        //2. 执行任务
        doSomethingB() ;
        try {
            //同步等待线程a结束
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //如果不等待：线程b时间设置为1s，线程a设置为2s，会导致，先执行完线程b，主线程不等待A执行完,
                // doSomething b
                //1104
                // doSomething a
        System.out .println(System.currentTimeMillis() - start) ;
    }
}
