package com.study.majinhu.Concurrent.countdownAndCyclic;

import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierTest
 * @Description
 * CyclicBarrier(同步屏障)
 *
 * 作用：当一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，
 * 所有被屏障拦截的线程才会继续执行。
 *
 * CyclicBarrier的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，
 * 每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。
 * ---------------------
 * 作者：chenkaibsw
 * 来源：CSDN
 * 原文：https://blog.csdn.net/chenkaibsw/article/details/80937189
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Author majinhu
 * @Date 2019/7/17 9:01
 * @Version 1.0
 **/
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int N = 4;
        //CyclicBarrier barrier = new CyclicBarrier(N);
        //CyclicBarrier还提供了更高级的构造函数，用于在线程到达屏障时，优先执行barrierAction。
        //public CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier barrier  = new CyclicBarrier(N,new A());

        for (int i = 0; i < N; i++)
            new Writer(barrier).start();
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println("所以线程写入完毕后，我优先执行");
        }
    }

}