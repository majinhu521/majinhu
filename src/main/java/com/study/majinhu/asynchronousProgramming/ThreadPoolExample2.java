package com.study.majinhu.asynchronousProgramming;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolExample2
 * @Description 线程池执行:无需要获取结果。
 * 建了一个线程池，这里我 设置线程池核 线程个数为当前物理机的
 * CPU 核数， 线程个数为当前物理机 CPU 核数的 倍；设置线程池阻 队列的大小
 * ；需要注意的是，我们将线程池的拒绝策略设置为 CallerRunsPolicy ，即当线程池
 * 任务饱和，执行拒绝策略时不会丢弃新的任务，而是会使用调用线程来执行；另外我们
 * 使用了命名的线程创建工厂 ，以便排 问题时可以方便追溯 哪个相关业务
 * 创建完线程池后，代码 则把异步任务提 到了线程池内运行，而不是 接开启一
 * 个新线程来运行；这里使用线程池起到了 用线程的作用 ，避免 线程 频繁 建与销
 * 毁，另外对线程个数也起到了限制作用
 * main 函数所在线程只需要把两个任务提交到线程池后就可以做
 * 己的 情了， 具体两个任务是 程池中的线程执行
 * @Author majinhu
 * @Date 2020/11/11 9:42
 * @Version 1.0
 **/
public class ThreadPoolExample2 {

    // 自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR =
    new ThreadPoolExecutor(AVALIABLE_PROCESSORS ,AVALIABLE_PROCESSORS * 2 ,
            1 , TimeUnit.MINUTES, new LinkedBlockingQueue<>(5) ,
    new ThreadPoolExecutor .CallerRunsPolicy() ) ;

    public static void doSomethingA() {
        try {

            Thread.sleep(2000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" doSomething a ");
    }



    public static void doSomethingB() {
        try {
            Thread.sleep(1000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" doSomething b ");
    }
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        //1. 执行任务a
        POOL_EXECUTOR.execute(()->{
            doSomethingA();
        });

        //2. 执行任务b
        POOL_EXECUTOR.execute(()->{
            doSomethingB();
        });

        System.out.println(System.currentTimeMillis() - start) ;
        System.out.println(Thread.currentThread().getName());
        try {
            //挂起当前线程,之前是新线程加进来。
            Thread.currentThread().join();
            //thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - start) ;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
