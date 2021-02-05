package com.study.majinhu.asynchronousProgramming;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestCompletableFutureSet
 * @Description
 * @Author majinhu
 * @Date 2020/11/14 19:24
 * @Version 1.0
 **/
public class TestCompletableFutureSet {
    // 自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS ,AVALIABLE_PROCESSORS * 2 ,
                    1 , TimeUnit.MINUTES, new LinkedBlockingQueue<>(5) ,
                    new ThreadPoolExecutor .CallerRunsPolicy() ) ;

    public static void main(String[] args) throws  Exception {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        POOL_EXECUTOR.execute(()->{
            try {
                Thread.sleep(3000);
                System.out.println("doing some thing");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete("done complete");
        });
        System.out.println("main wating result ====");
        System.out.println(completableFuture.get());
        System.out.println("main got result ====");
    }
}
