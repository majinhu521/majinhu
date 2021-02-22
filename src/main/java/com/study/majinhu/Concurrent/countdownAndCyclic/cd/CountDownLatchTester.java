package com.study.majinhu.Concurrent.countdownAndCyclic.cd;

/**
 * @ClassName CountDownLatchTester
 * @Description 测试类
 * https://zhuanlan.zhihu.com/p/150180987
 * https://www.cnblogs.com/yrjns/p/12163745.html
 * @Author majinhu
 * @Date 2021/2/18 10:58
 * @Version 1.0
 * 10:59:23.417 张三出发去饭店
 * 10:59:23.417 王五出发去饭店
 * 10:59:23.417 李四出发去饭店
 * 10:59:23.425 ♥小芳♥等待顾客
 * 10:59:24.434 张三到了饭店
 * 10:59:25.384 王五到了饭店
 * 10:59:25.485 李四到了饭店
 * 10:59:25.485 ♥小芳♥开始上菜
 **/
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTester {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        List<Thread> threads = new ArrayList<>(3);
        threads.add(new Thread(new Customer(latch, "张三")));
        threads.add(new Thread(new Customer(latch, "李四")));
        threads.add(new Thread(new Customer(latch, "王五")));
        for (Thread thread : threads) {
            thread.start();
        }

        Thread.sleep(100);
        new Thread(new Waitress(latch, "♥小芳♥")).start();

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
