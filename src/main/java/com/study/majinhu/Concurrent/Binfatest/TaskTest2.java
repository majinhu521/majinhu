package com.study.majinhu.Concurrent.Binfatest;

import com.google.common.util.concurrent.AbstractFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName TaskTest
 * @Description Future<String> 异步返回结果；异步回调
 * @Author majinhu
 * @Date 2019/6/21 13:46
 * @Version 1.0
 **/
@Component
public class TaskTest2 {
    public static Random random = new Random();
    private Object AsyncResult;
    private Object String;

    @Async
    public Future<String> doTaskOne() throws Exception{
        System.out.println("开始任务1");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("结束任务1-------"+(end-start)+"毫秒");
        return new AsyncResult<>("任务1完成");
    }
    @Async
    public Future<String>  doTaskTwo(){
        System.out.println("开始任务2");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("结束任务2-------"+(end-start)+"毫秒");
        return new AsyncResult<>("任务2完成");
    }
    @Async
    public Future<String>  doTaskThree(){
        System.out.println("开始任务3");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("结束任务3-------"+(end-start)+"毫秒");
        return new AsyncResult<>("任务3完成");
    }

}
