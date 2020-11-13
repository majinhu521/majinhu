package com.study.majinhu.Concurrent.threadlocal;

import org.springframework.boot.SpringApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadlocalApplication
 * @Description https://www.jianshu.com/p/9a49ed06e936
 * 设置了-Xms100m -Xmx100m
 * @Author majinhu
 * @Date 2020/9/7 9:29
 * @Version 1.0
 **/
public class ThreadlocalApplication {
    public static ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(99);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            exec.execute(() -> {
                threadLocal.set(new byte[1024 * 1024]);
                System.out.println("设置对象1M"+ finalI);
                threadLocal.remove();
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                    //TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    threadLocal.remove();
                }
            });
        }
    }
}
