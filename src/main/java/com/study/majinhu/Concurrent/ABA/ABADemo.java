package com.study.majinhu.Concurrent.ABA;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName ABADemo
 * @Description ABA 问题
 * https://www.cnblogs.com/lmj612/p/10836912.html
 * https://blog.csdn.net/bjweimengshu/article/details/79000506
 * @Author majinhu
 * @Date 2019/6/27 9:16
 * @Version 1.0
 **/
public class ABADemo {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);

    //初始值为100，线程t1将100改成101，然后又将101改回100
    //线程t2先睡眠1秒，等待t1操作完成，然后t2线程将值改成2019
   // 可以看到，线程2修改成功
    public static void main(String[] args) {
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        },"t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t修改后的值:" + atomicReference.get());
        },"t2").start();
    }
}
