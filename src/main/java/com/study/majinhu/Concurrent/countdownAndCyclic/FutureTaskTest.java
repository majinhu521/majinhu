package com.study.majinhu.Concurrent.countdownAndCyclic;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName FutureTaskTest
 *FutureTask 可取消的异步计算。这个类提供了Future的基本实现，提供了启动和取消计算、
 * 查询计算是否完成以及检索计算结果的方法。计算完成后才能检索结果;如果计算尚未完成，
 * get方法将阻塞。一旦计算完成，就不能重新启动或取消计算(除非使用runAndReset()调用计算)。
 * FutureTask可用于包装可调用或可运行的对象。因为FutureTask实现Runnable，所以可以将FutureTask提交给执行程序执行。
 * 除了作为独立类使用外，该类还提供了在创建自定义任务类时可能有用的受保护功能。
 *
 *  原文：https://blog.csdn.net/u010904188/article/details/87602836
 * @Author majinhu
 * @Date 2019/7/17 10:40
 * @Version 1.0
 **/
public class FutureTaskTest {
    /* 实现Callable接口，允许有返回值 */
    private static class UseCallable implements Callable< Integer > {

        private int sum;

        @Override
        public Integer call() throws Exception {
            System.out.println("Callable子线程开始计算");
            Thread.sleep(2000);
            for (int i = 0; i < 100; i++) {
                sum = sum + i;
            }
            System.out.println("Callable子线程计算完成，结果=" + sum);
            return sum;
        }

    }

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {

        UseCallable useCallable = new UseCallable();
        FutureTask< Integer > futureTask = new FutureTask < Integer >(
                useCallable);
        new Thread(futureTask).start();
        Random r = new Random();
        Thread.sleep(1000);
        if (r.nextBoolean()) {// 随机决定是获得结果还是终止任务
            System.out.println("Get UseCallable result = " + futureTask.get());
        }
        else {
            System.out.println("中断计算");
            futureTask.cancel(true);
        }

    }


}
