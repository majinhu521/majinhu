package com.study.majinhu.asynchronousProgramming;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @ClassName TestCompletableFutureSet
 * 异步任务
 *   //runAsync 无返回值得异步计算。
 * @Description
 * @Author majinhu
 * @Date 2020/11/14 19:24
 * @Version 1.0
 **/
public class TestCompletableFutureSet2 {
    // 自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    //private final static  ThreadFactory threadFactory;
    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS, AVALIABLE_PROCESSORS * 2,
                    1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
                    new ThreadFactoryBuilder().setNameFormat("ASYNC_POOL").build(),
                    new ThreadPoolExecutor .CallerRunsPolicy() ) ;

    public static void main(String[] args) throws  Exception {
        //runAsync 无返回值得异步计算。
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+" doing some thing");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("over");
            }
        });
        //无返回值.
        System.out.println(future.get());

        //自定义线程池传入,无返回值
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    //默认线程池为
                    //ForkJoinPool.commonPool();
                    System.out.println(Thread.currentThread().getName()
                            +"/ ForkJoinPool.commonPool doing some thing");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("over");
            }
        },POOL_EXECUTOR);
        //无返回值.
        System.out.println(future2.get());


        //自定义线程池传入,异步返回.
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(2000);
                    //默认线程池为
                    //ForkJoinPool.commonPool();
                    System.out.println(Thread.currentThread().getName()
                            +"/ ForkJoinPool.commonPool doing some thing");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("supplyAsync over");
                return "supplyAsync over";
            }
        },POOL_EXECUTOR);

        //supplyAsync获取返回值
        System.out.println(future3.get());


        
    }
}
