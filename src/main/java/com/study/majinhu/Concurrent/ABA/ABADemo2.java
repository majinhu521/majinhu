package com.study.majinhu.Concurrent.ABA;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName ABADemo2
 * @Description
 * 要解决ABA问题，可以增加一个版本号，当内存位置V的值每次被修改后，版本号都加1
 *
 * AtomicStampedReference
 * AtomicStampedReference内部维护了对象值和版本号，在创建AtomicStampedReference对象时，需要传入初始值和初始版本号，
 * 当AtomicStampedReference设置对象值时，对象值以及状态戳都必须满足期望值，写入才会成功
 *
 * 1、初始值100，初始版本号1
 * 2、线程t1和t2拿到一样的初始版本号
 * 3、线程t1完成ABA操作，版本号递增到3
 * 4、线程t2完成CAS操作，最新版本号已经变成3，跟线程t2之前拿到的版本号1不相等，操作失败
 * @Author majinhu
 * @Date 2019/6/27 9:22
 * @Version 1.0
 **/
public class ABADemo2 {
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("t1拿到的初始版本号:" + atomicStampedReference.getStamp());

            //睡眠1秒，是为了让t2线程也拿到同样的初始版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            atomicStampedReference.compareAndSet(101, 100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
        },"t1").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("t2拿到的初始版本号:" + stamp);

            //睡眠3秒，是为了让t1线程完成ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("最新版本号:" + atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(100, 2019,stamp,atomicStampedReference.getStamp() + 1) + "\t当前 值:" + atomicStampedReference.getReference());
        },"t2").start();
    }
}
