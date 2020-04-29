package com.study.majinhu.Concurrent.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName ThreadTestPark
 * @Description
 * park()方法，调用native方法阻塞当前线程
 *
 * unpark()方法，唤醒处于阻塞状态的线程Thrread
 *
 * park翻译过来即是停车的意思，我们可以这样理解，每个被应用程序启动的线程就是一辆在计算机总线赛道上奔驰着的跑车，
 * 当你想让某台车停下来休息会时那么就给它一个park信号，它就会立即停到赛道旁边的停车位中，
 * 当你想让它从停车位中驶出并继续在赛道上奔跑时再给它一个unpark信号即可
 *
 * 作者：MMTTMM
 * 链接：https://www.imooc.com/article/48378
 * 来源：慕课网
 * @Author majinhu
 * @Date 2019/6/27 14:35
 * @Version 1.0
 **/
public class ThreadTestPark {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.setName("mt");
        mt.start();
        try {
            Thread.currentThread().sleep(10);
            mt.park();
            Thread.currentThread().sleep(10000);
            mt.unPark();
            Thread.currentThread().sleep(10000);
            mt.park();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {
        private boolean isPark = false;

        public void run() {
            System.out.println(" Enter Thread running.....");
            while (true) {
                if (isPark) {
                    System.out.println(Thread.currentThread().getName() + "Thread is Park.....");
                    LockSupport.park();
                }
                //do something
                System.out.println(Thread.currentThread().getName() + ">> is running");
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public void park() {
            isPark = true;
        }

        public void unPark() {
            isPark = false;
            LockSupport.unpark(this);
            System.out.println("Thread is unpark.....");
        }
    }
}

