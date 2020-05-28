package com.study.majinhu.Concurrent.lock.enjoylock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName PushOrder
 * @Description AtomicInteger,确保了原子性，cas机制。自旋。
 * cas机制：比较交换。 工作内存和共享内存的值进行比较，如果一致，就进行交换。不一致进行下次循环，继续尝试。
 * 会有ABA问题。 解决ABA问题。加相关版本号判断。拿上次的版本号进行比较，是否一致。
 * lock锁可以解决ABA问题，多个线程只有1个线程获取锁，获取锁的对象可进行操作。其他的进行等待。
 * @Author majinhu
 * @Date 2020/5/21 9:06
 * @Version 1.0
 **/
public class PushOrderAtomic {
    AtomicInteger i = new AtomicInteger(60000);
    public void order(){
        i.decrementAndGet();//操作具备原子性，要么成功，要么失败。unsafe直接操作内存。
    }
    public static void main(String[] args) throws InterruptedException {
        //开启6个线程，每个线程执行10000次减1的操作。
        PushOrderAtomic pushOrder = new PushOrderAtomic();
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
