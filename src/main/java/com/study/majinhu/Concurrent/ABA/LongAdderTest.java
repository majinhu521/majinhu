package com.study.majinhu.Concurrent.ABA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * @ClassName LongAdderTest
 * @Description
 *     作者：抽离的心
 *     来源：CSDN
 *     原文：https://blog.csdn.net/u011635492/article/details/81583105
 *     版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Author majinhu
 * @Date 2019/6/27 14:04
 * @Version 1.0
 **/
public class LongAdderTest {
    private static final Logger log = LoggerFactory.getLogger(LongAdderTest.class);

    private static int clientTotal=5000;

    private static int threadTotal=200;

    public static LongAdder count=new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("InterruptedException",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("count:{}",count);
        executorService.shutdown();
    }

    private static void add(){
        count.increment();
    }

}
