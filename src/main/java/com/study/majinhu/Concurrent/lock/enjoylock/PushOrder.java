package com.study.majinhu.Concurrent.lock.enjoylock;

/**
 * @ClassName PushOrder
 * @Description 普通不加锁的情况，由于变量不可见和jvm内存模型，导致库存不会减到0
 * @Author majinhu
 * @Date 2020/5/21 9:06
 * @Version 1.0
 **/
public class PushOrder {
    int i = 60000;
    public void order(){
        i--;
    }
    public static void main(String[] args) throws InterruptedException {
        //开启6个线程，每个线程执行10000次减1的操作。
        PushOrder pushOrder = new PushOrder();
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
