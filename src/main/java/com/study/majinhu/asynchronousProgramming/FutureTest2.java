package com.study.majinhu.asynchronousProgramming;

import javafx.concurrent.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName FutureTest2
 * @Description https://www.liaoxuefeng.com/wiki/1252599548343744/1306581155184674
 * @Author majinhu
 * @Date 2020/11/11 11:23
 * @Version 1.0
 **/
public class FutureTest2 {
    static ExecutorService executor = Executors.newFixedThreadPool(4);

    static class Task implements Callable<String> {
        public String call() throws Exception {
            return "done Task result ";
        }
    }
    // 定义任务:
    static Callable<String> task = new Task();

    public static void main(String[] args) throws  Exception{
        // 提交任务并获得Future:
        Future<String> future = executor.submit(task);
        // 从Future获取异步执行返回的结果:
        String result = future.get(); // 可能阻塞
        System.out.println(result);
    }
}
