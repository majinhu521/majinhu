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
public class TicketTest {
    static int tickets=10;
    class SellTickets implements Runnable{
        @Override
        public void run() {
        // 未加同步时,产生脏数据
            while(tickets>0){
                synchronized (this){ //超卖了。不加会有线程安全问题，多个窗口抢到同一个票。
                    //4号窗口 -->售出第 2 张票
                    //2号窗口 -->售出第 1 张票
                    //1号窗口 -->售出第 0 张票
                    //3号窗口 -->售出第 -1 张票
                    System.out.println(Thread.currentThread().getName()+" -->售出第 "+tickets+" 张票");
                    tickets--;
                }
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
    }
    public static void main(String[] args) {
        SellTickets sell=new TicketTest().new SellTickets();
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
