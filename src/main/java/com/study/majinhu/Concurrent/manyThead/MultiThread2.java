package com.study.majinhu.Concurrent.manyThead;

/**
 * @ClassName MultiThread
 * @Description
 * Runnable
 * 实现Runnable
 * 实现run()方法
 *
 * Thread
 * 继承Thread
 * 重写run()方法
 *
 * Callable
 * 实现Callable
 * 实现call()方法
 * 有返回值
 * 可以将异常抛出
 * @Author majinhu
 * @Date 2021/2/20 16:17
 * @Version 1.0
 **/
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class MultiThread2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is run;");
        // 继承Thread
        final Thread thread1 = new MyThread();
        thread1.start();
        // 实现Runnable
        final Thread thread2 = new Thread(new MyRunnable());
        thread2.start();
        //实现 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        final FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        final Thread thread3 = new Thread(futureTask);
        thread3.start();
        // 获取计算的结果
        // get方法会阻塞，直到计算完成
        System.out.println(futureTask.get());

    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is run;");
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is run;");
    }
}

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        return Thread.currentThread().getName() + " is run;";
    }
}
