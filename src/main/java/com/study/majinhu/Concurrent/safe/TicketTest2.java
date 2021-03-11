package com.study.majinhu.Concurrent.safe;

/**
 * @ClassName TicketTest
 * @Description 线程安全 三种方式
 * https://www.cnblogs.com/lizhangyong/p/8029287.html
 *
 * 当进程中有多个并发线程进入一个重要数据的代码块时，在修改数据的过程中，很有可能引发线程安全问题，
 * 从而造成数据异常。
 * 例如，正常逻辑下，同一个编号的火车票只能售出一次，却由于线程安全问题而被多次售出，从而引起实际业务异常。
 * @Author majinhu
 * @Date 2021/3/11 15:21
 * @Version 1.0
 **/
public class TicketTest2 {
    static int tickets=10;
    class SellTickets implements Runnable{
        @Override
        public void run() {
            //同步方法
            while(tickets>0){
                synMethod();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(tickets<=0){
                System.out.println(Thread.currentThread().getName()+" -->售票结束!");
            }
        }
        //同步方法
        synchronized void synMethod(){
            synchronized(this){
                if(tickets<=0){
                    return;
                }
                System.out.println(Thread.currentThread().getName()+" -->售出第 "+tickets+" 张票");
                tickets--;
            }
        }
    }
    public static void main(String[] args) {
        SellTickets sell=new TicketTest2().new SellTickets();
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
