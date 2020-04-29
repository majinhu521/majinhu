package com.study.majinhu.Concurrent.countdownAndCyclic;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName TestCountDownLatch
 * @Description
 * CounDownLatch对于管理一组相关线程非常有用。上述示例代码中就形象地描述了两种使用情况。
 * 第一种是计算器为1，代表了两种状态，开 关。第二种是计数器为N，代表等待N个操作完成。
 * @Author majinhu
 * @Date 2019/7/1 10:26
 * @Version 1.0
 **/
public class TestCountDownLatch {
    private static final int N = 10;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(N);
        CountDownLatch startSignal = new CountDownLatch(1);//开始执行信号

        for (int i = 1; i <= N; i++) {
            new Thread(new Worker(i, doneSignal, startSignal)).start();//线程启动了
        }
        System.out.println("begin------------");
        startSignal.countDown();//开始执行啦
        doneSignal.await();//等待所有的线程执行完毕
        System.out.println("Ok");

    }

    static class Worker implements Runnable {
        private final CountDownLatch doneSignal;
        private final CountDownLatch startSignal;
        private int beginIndex;

        Worker(int beginIndex, CountDownLatch doneSignal,
               CountDownLatch startSignal) {
            this.startSignal = startSignal;
            this.beginIndex = beginIndex;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await(); //等待开始执行信号的发布
                beginIndex = (beginIndex - 1) * 10 + 1;
                for (int i = beginIndex; i <= beginIndex + 10; i++) {
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                doneSignal.countDown();
            }
        }
    }
}
