package com.study.majinhu.Concurrent.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadForpools
 * @Description 处理线程
 * @Author majinhu
 * @Date 2019/6/19 16:55
 * @Version 1.0
 **/
public class ThreadForpools implements Runnable{

    private Integer index;
    public  ThreadForpools(Integer index)
    {
        this.index=index;
    }
    @Override
    public void run() {
        /***
         * 业务......省略
         */
        try {
            System.out.println("开始处理线程！！！");
            Thread.sleep(index*100);
            System.out.println("我的线程标识是："+this.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 我们获取四次次线程，观察4个线程地址

     * @param args
     */
    public static  void main(String[]args)
    {

        //(1) newCachedThreadPool
        //      创建一个可缓存线程池，应用中存在的线程数可以无限大
        //       输出结果是：可以有无限大的线程数进来（线程地址不一样）

//        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
//        System.out.println("****************************newCachedThreadPool*******************************");
//        for(int i=0;i<4;i++)
//        {
//            final int index=i;
//            newCachedThreadPool.execute(new ThreadForpools(index));
//        }


        //(2) newFixedThreadPool
        // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        //线程池允许同时存在两个线程
        //输出结果：每次只有两个线程在处理，当第一个线程执行完毕后，新的线程进来开始处理（线程地址不一样）

//        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
//        System.out.println("****************************newFixedThreadPool*******************************");
//        for(int i=0;i<4;i++)
//        {
//            final int index=i;
//            newFixedThreadPool.execute(new ThreadForpools(index));
//        }


        //(3) newSingleThreadExecutor
        // 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
        //线程池允许同时存在1个线程
        //输出结果：执行结果：只存在一个线程，顺序执行

//            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
//            System.out.println("****************************newFixedThreadPool*******************************");
//            for(int i=0;i<4;i++)
//            {
//                final int index=i;
//                newSingleThreadExecutor.execute(new ThreadForpools(index));
//            }
//        }

        // (3)  newScheduledThreadPool
        // 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
        //  执行结果：延迟三秒之后执行，除了延迟执行之外和newFixedThreadPool基本相同，可以用来执行定时任务
    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
        System.out.println("****************************newFixedThreadPool*******************************");
               for(int i=0;i<4;i++)
            {
                final int index=i;
                //延迟三秒执行
                newScheduledThreadPool.schedule(new ThreadForpools(index),3, TimeUnit.SECONDS);
            }
        }



}