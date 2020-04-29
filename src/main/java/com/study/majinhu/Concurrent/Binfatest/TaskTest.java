package com.study.majinhu.Concurrent.Binfatest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @ClassName TaskTest
 * @Description Async，异步不等待结果。无序执行。
 * @Author majinhu
 * @Date 2019/6/21 13:46
 * @Version 1.0
 **/
@Component
public class TaskTest {
    public static Random random = new Random();
    @Async
    public void doTaskOne(){
        System.out.println("开始任务1");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("结束任务1-------"+(end-start)+"毫秒");
    }
    @Async
    public void doTaskTwo(){
        System.out.println("开始任务2");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("结束任务2-------"+(end-start)+"毫秒");
    }
    @Async
    public void doTaskThree(){
        System.out.println("开始任务3");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("结束任务2-------"+(end-start)+"毫秒");
    }

}
