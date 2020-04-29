package com.study.majinhu.Concurrent.countdownAndCyclic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierTest3
 * @Description
 * CyclicBarrier相比CountDownLatch更强大，提供了其他有用的方法，比如isBroken()方法用来了解阻塞线程是否被中断。
 * @Author majinhu
 * @Date 2019/7/17 9:09
 * @Version 1.0
 **/
public class CyclicBarrierTest3 {
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    c.await();
                    System.out.println("这里没有被执行");
                } catch (Exception e) {
                    System.out.println("线程被中断");
                }
            }
        });
        thread.start();
        thread.interrupt();
        try {
            c.await();
        } catch (Exception e) {
            System.out.println("了解线程是否被中断："+c.isBroken());
        }
    }

}
