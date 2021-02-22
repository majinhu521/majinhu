package com.study.majinhu.Concurrent.manyThead;

/**
 * @ClassName ThreadPoolDemo
 * @Description
 * @Author majinhu
 * @Date 2021/2/20 16:26
 * @Version 1.0
 **/
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 单个线程
        final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        // 固定数量线程
        final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        // 可以自动扩容的线程池
        final ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        // 定时执行线程池
        final ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(3);
        // 窃取队列线程池-默认系统可用处理器数量
        // 多CPU计算机上处理性能更佳
        final ForkJoinPool newWorkStealingPool = (ForkJoinPool) Executors.newWorkStealingPool(3);


        // 延迟1秒，每2秒执行一次
        newScheduledThreadPool.scheduleAtFixedRate(new MyRunnablePool(), 1, 2, TimeUnit.SECONDS);
        // 提交任务 到 ForkJoinPool
        final ForkJoinTask<Long> forkJoinTask = newWorkStealingPool.submit(new MyRecursiveTask(0, 1000));
        // java 8 parallel 并行流
        final long longReduce = LongStream.rangeClosed(0, 1000L).parallel().reduce(0, Long::sum);
        System.out.println("longReduce = " + longReduce);
        System.out.println("forkJoinTask = " + forkJoinTask.get());

        for (int i = 0; i < 10; i++) {
            // 线程2 执行较慢。其他线程会窃取他的任务，多执行
            newWorkStealingPool.execute(new MyRunnablePool());
            // 延迟2秒后执行
            newScheduledThreadPool.schedule(new MyRunnablePool(), 2, TimeUnit.SECONDS);
            // 每次都创建新的线程
            newCachedThreadPool.execute(new MyRunnablePool());
            // 固定数量线程
            newFixedThreadPool.execute(new MyRunnablePool());
            //单一线程
            singleThreadExecutor.execute(new MyRunnablePool());
        }
        TimeUnit.SECONDS.sleep(5);
        for (int i = 0; i < 10; i++) {
            // 复用 线程池中空闲的线程
            newCachedThreadPool.execute(new MyRunnablePool());
        }
    }
}

class MyRecursiveTask extends RecursiveTask<Long> {
    private final int from;
    private final int to;

    public MyRecursiveTask(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        if (to - from < 6) {
            long total = 0;
            for (int i = from; i <= to; i++) {
                total += i;
            }
            return total;
        } else {
            // 否则，把任务一分为二，递归拆分(注意此处有递归)到底拆分成多少分 需要根据具体情况而定
            int middle = (from + to) / 2;
            MyRecursiveTask taskLeft = new MyRecursiveTask(from, middle);
            MyRecursiveTask taskRight = new MyRecursiveTask(middle + 1, to);
            taskLeft.fork();
            taskRight.fork();
            return taskLeft.join() + taskRight.join();
        }
    }
}
class MyRunnablePool implements Runnable {

    @Override
    public void run() {
        try {
            if (Thread.currentThread().getName().contains("2")) {
                // 线程 2 多睡眠1秒
                TimeUnit.SECONDS.sleep(1);
            }
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is run;");
    }
}
