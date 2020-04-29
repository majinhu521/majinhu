package com.study.majinhu.Concurrent.ABA;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @ClassName ABADemo3
 * @Description  AtomicMarkableReference
 *
 * AtomicStampedReference可以给引用加上版本号，追踪引用的整个变化过程，如：A -> B -> C -> D - > A，通过AtomicStampedReference，我们可以知道，引用变量中途被更改了3次
 * 但是，有时候，我们并不关心引用变量更改了几次，只是单纯的关心是否更改过，所以就有了AtomicMarkableReference
 * AtomicMarkableReference的唯一区别就是不再用int标识引用，而是使用boolean变量——表示引用变量是否被更改过
 * @Author majinhu
 * @Date 2019/6/27 9:43
 * @Version 1.0
 **/
public class ABADemo3 {
    private static AtomicMarkableReference<Integer> atomicMarkableReference = new AtomicMarkableReference<Integer>(100,false);

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("t1版本号是否被更改:" + atomicMarkableReference.isMarked());

            //睡眠1秒，是为了让t2线程也拿到同样的初始版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicMarkableReference.compareAndSet(100, 101,atomicMarkableReference.isMarked(),true);
            atomicMarkableReference.compareAndSet(101, 100,atomicMarkableReference.isMarked(),true);
        },"t1").start();

        new Thread(() -> {
            boolean isMarked = atomicMarkableReference.isMarked();
            System.out.println("t2版本号是否被更改:" + isMarked);

            //睡眠3秒，是为了让t1线程完成ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("是否更改过:" + atomicMarkableReference.isMarked());
            System.out.println(atomicMarkableReference.compareAndSet(100, 2019,isMarked,true) + "\t当前 值:" + atomicMarkableReference.getReference());
        },"t2").start();
    }

}
