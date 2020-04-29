package com.study.majinhu.Concurrent.reentrantTest;

/**
 * @ClassName Store2
 * @Description Condition条件
 * Condition接口提供的基本方法如下：
 *
 * void await() throws InterruptedException;
 * void awaitUninterruptibly();
 * long awaitNanos(long nanosTimeout) throws InterruptedException;
 * boolean await(long time,TimeUnit unit)throws InterruptedException;
 * void signal();
 * void signalAll();

 * await()方法会使当前线程等待，同时释放当前锁，当其他线程使用signal()或signalAll()方法的时候，线程会重新获得锁并继续执行。
 * 或者当线程被中断的时，也能跳出等待。跟Object.wait()方法很像。
 * awaitUninterruptibly()跟await方法基本相同，区别仅仅在于它不会在等待过程中响应中断。
 * signal()方法用于唤醒一个在等待中的线程。相对的signalAll()方法会唤醒所有在等待中的线程，跟Object.notify()很像。

 * 用Condition来改造以前讲的notify()和wait()的生产者消费者的例子：
 * @Author majinhu
 * @Date 2019/6/20 17:33
 * @Version 1.0
 **/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class  Store2 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private final int MAX_SIZE;  //仓库的最大容量
    private int count;           //当前的仓库数量
    public Store2(int n){
        MAX_SIZE = n;
        count = 0;
    }
    //往仓库加货物的方法
    public void add(){       //使用了wait()或notify()方法，需要加上syncronized关键字
        try {
            lock.lock();
            while (count >= MAX_SIZE) {          //否则可能会抛出java.lang.IllegalMonitorStateException
                System.out.println("已经满了");
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            //打印当前仓库的货物数量
            System.out.println(Thread.currentThread().toString() + " put" + count);
            //仓库中已经有东西可以取了，则通知所有的消费者线程来拿
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
    //从仓库拿走货物的方法
    public  void remove(){
        try {
            lock.lock();
            while (count <= 0) {
                System.out.println("空了");
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //打印当前仓库的货物数量
            System.out.println(Thread.currentThread().toString() + " get" + count);
            count--;
            //仓库还没装满，通知生产者添加货物
            notFull.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Store2 s = new Store2(5);         //创建容量为5的仓库
        //创建两个生产者和两个消费者
        Thread pro = new Producer2(s);
        Thread con = new Consumer2(s);
        Thread pro2 = new Producer2(s);
        Thread con2 = new Consumer2(s);
        pro.setName("producer");
        con.setName("consumer");
        pro2.setName("producer2");
        con2.setName("consumer2");
        //启动各个线程
        pro.start();
        pro2.start();
        con.start();
        con2.start();
    }
}
class Producer2 extends Thread{          //生产者线程类
    private Store2 s;
    public Producer2(Store2 s){
        this.s = s;
    }
    public void run(){      //线程方法
        while(true){        //永久循环
            s.add();        //往仓库加货物
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
class Consumer2 extends Thread {          //消费者线程类
    private Store2 s;

    public Consumer2(Store2 s) {
        this.s = s;
    }

    public void run() {      //线程方法
        while (true) {        //永久循环
            s.remove();        //往仓库取走货物
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}