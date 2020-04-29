package com.study.majinhu.Concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName ThreadTest
 * @Description 测试线程类的方法1
 * @Author majinhu
 * @Date 2019/6/19 9:31
 * @Version 1.0
 **/
public class ThreadTest {
    //方法一：继承 Thread 类并重写 run 方法
    /**
     * @Author majinhu
     * @Description
     *  MyThread 类继承了 Thread 类，并重写了 run 方法，然后调用了线程的 start 方法启动了线程，当创建完 thread 对象后该线程并没有被启动执行.
     * 当调用了 start 方法后才是真正启动了线程。其实当调用了 start 方法后线程并没有马上执行而是处于就绪状态，这个就绪状态是指该线程已经获取了除 CPU 资源外的其它资源，等获取 CPU 资源后才会真正处于运行状态。
     * 当 run 方法执行完毕，该线程就处于终止状态了。使用继承方式好处是 run 方法内获取当前线程直接使用 this 就可以，无须使用 Thread.currentThread() 方法，不好的地方是 Java 不支持多继承，如果继承了 Thread 类那么就不能再继承其它类。
     * 另外任务与代码没有分离，当多个线程执行一样的任务时候需要多份任务代码，而 Runable 则没有这个限制
     * @Date 9:39 2019/6/19
     * @Param
     * @return
     **/
    public static class Mythread extends Thread {
        @Override
        public void run() {
            System.out.println("run 运行");
        }

        public static void main(String[] args) {
            // 创建线程
            Mythread thread = new Mythread();
            // 启动线程
            thread.start();
        }
    }

    //方法2：实现 Runnable 接口的 run 方法方式
    /**
     * @Author majinhu
     * @Description
     * 两个线程公用一个 task 代码逻辑，需要的话 RunableTask 可以添加参数进行任务区分，另
     * 外 RunableTask 可以继承其他类，但是上面两种方法都有一个缺点就是任务没有返回值
     * @Date 9:42 2019/6/19
     * @Param 
     * @return 
     **/
    public static class RunableTask implements Runnable{
        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }
    public static void main(String[] args)  {
        RunableTask task = new RunableTask();
        new Thread(task).start();
        new Thread(task).start();
    }

    //方法3：FutureTask方法方式,有返回值
    public static class CallerTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "hello";
        }

    public static void main(String[] args) throws InterruptedException {
            // 创建异步任务
            FutureTask<String> futureTask  = new FutureTask<>(new CallerTask());
            //启动线程
            new Thread(futureTask).start();
            try {
                //等待任务执行完毕，并返回结果
                String result = futureTask.get();
                System.out.println(result);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }



}