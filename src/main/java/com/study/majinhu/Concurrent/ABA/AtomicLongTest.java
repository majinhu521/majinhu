package com.study.majinhu.Concurrent.ABA;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @ClassName AtomicLongTest
 * @Description https://blog.csdn.net/u011635492/article/details/81583105
 * @Author majinhu
 * @Date 2019/6/27 13:58
 * @Version 1.0
 **/
@Slf4j
public class AtomicLongTest {
    private static final Logger log = LoggerFactory.getLogger(AtomicLongTest.class);

    private static int clientTotal=5000;

    private static int threadTotal=200;

    public static AtomicLong count=new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
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
        count.getAndIncrement();
    }

}
