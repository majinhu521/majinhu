package com.study.majinhu.asynchronousProgramming;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.tomcat.util.threads.TaskThreadFactory;

import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolFutureExample
 * @Description 线程池执行
 * future 获取线程的返回值
 * @Author majinhu
 * @Date 2020/11/11 9:42
 * @Version 1.0
 **/
public class ThreadPoolFutureExample {

    // 自定义线程池
//    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
//    //https://www.cnblogs.com/jhxxb/p/10833160.html
//    private final static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
//    private final static ThreadPoolExecutor POOL_EXECUTOR =
//    new ThreadPoolExecutor(AVALIABLE_PROCESSORS ,AVALIABLE_PROCESSORS * 2 ,
//            1 , TimeUnit.MINUTES, new LinkedBlockingQueue<>(5) ,
//            new ThreadFactoryBuilder().setNameFormat("ASYNC POOL").build(),
//            //namedThreadFactory,
//            new ThreadPoolExecutor .CallerRunsPolicy() ) ;

    static ExecutorService POOL_EXECUTOR = Executors.newFixedThreadPool(4);

    public static String doSomethingA() {
        try {
            Thread.sleep(2000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" doSomething a ");
        return "doSomething a Return done";
    }
    static class Task implements Callable<String> {
        public String call() throws Exception {
            return "done Task result ";
        }
    }
    // 定义任务:
//    static Callable<String> task = new Task() ;


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        //1. submit(Runnable)执行任务a(Runnable ,这种方法没有返回值的)
        Future<?> futureSubmitRunnable = POOL_EXECUTOR.submit(() -> {
            doSomethingA();
        });

        //2.submit(CALLABLE有返回值)
        Future<String> futureSubmitCallable = POOL_EXECUTOR.submit(new Task());
        System.out.println(System.currentTimeMillis() - start) ;
        System.out.println(Thread.currentThread().getName());
        System.out.println(futureSubmitRunnable.get());//获取不到返回值，null
        System.out.println(futureSubmitCallable.get());//可能阻塞
    }

}
