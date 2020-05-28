package com.study.majinhu.Concurrent.lock.enjoylock;

/**
 * @ClassName PushOrder
 * @Description synchronized 锁，重量锁，切换上下文+内核态用户态的切换。
 * @Author majinhu
 * @Date 2020/5/21 9:06
 * @Version 1.0
 **/
public class PushOrderSysc {
    int i = 60000;
    public synchronized void order(){
        i--;
    }
    public static void main(String[] args) throws InterruptedException {
        //开启6个线程，每个线程执行10000次减1的操作。
        PushOrderSysc pushOrder = new PushOrderSysc();
        for (int j = 0; j < 6; j++) {
            new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    pushOrder.order();
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println("库存量"+pushOrder.i);
    }
}
