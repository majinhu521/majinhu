package com.study.majinhu.Concurrent.safe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TicketTest
 * @Description 线程安全 三种方式
 * https://www.cnblogs.com/lizhangyong/p/8029287.html
 *第三种，Lock锁机制。
　　通过创建Lock对象，采用lock()加锁，采用unlock()解锁，来保护指定代码块。
 * @Author majinhu
 * @Date 2021/3/11 15:21
 * @Version 1.0
 **/
public class TicketTest3 {
    static int tickets=10;
    class SellTickets implements Runnable{
        Lock lock=new ReentrantLock();
        @Override
        public void run() {
        //Lock锁机制
            while(tickets>0){
                try{
                    lock.lock();
                    if(tickets<=0){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName()+" -->售出第 "+tickets+" 张票");
                    tickets--;
                }finally{
                    lock.unlock();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(tickets<=0){
                System.out.println(Thread.currentThread().getName()+" -->售票结束!");
            }
        }
    }
    public static void main(String[] args) {
        SellTickets sell=new TicketTest3().new SellTickets();
        Thread t1=new Thread(sell, "1号窗口");
        Thread t2=new Thread(sell, "2号窗口");
        Thread t3=new Thread(sell, "3号窗口");
        Thread t4=new Thread(sell, "4号窗口");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
