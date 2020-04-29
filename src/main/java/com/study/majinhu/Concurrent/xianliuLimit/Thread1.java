package com.study.majinhu.Concurrent.xianliuLimit;

/**
 * @ClassName Thread1
 * @Description
 * 一、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。
 * 另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
 * @Author majinhu
 * @Date 2019/8/16 9:28
 * @Version 1.0
 **/
public class Thread1 implements Runnable {
    public void run() {
        //synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()
                        + " synchronized loop " + i);
            }
        //}
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();

        Thread ta = new Thread(t1);
        Thread tb = new Thread(t1);
        ta.start();
        tb.start();
    }
}
