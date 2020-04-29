package com.study.majinhu.Concurrent.ABA;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LazySetDemo
 * @Description
 * https://blog.csdn.net/qq_37212210/article/details/92815391
 * https://www.jianshu.com/p/45f7e31c6792
 * @Author majinhu
 * @Date 2019/6/27 10:37
 * @Version 1.0
 **/
public class LazySetDemo {
    AtomicInteger atomicInteger = new AtomicInteger(10);

    Lock lock = new ReentrantLock();

//
////    try{
////        lock.lock();
//        lock.
////            atomicInteger.set(2333);//变量由volatile关键字修饰，会使用到内存屏障，在锁内其实是不必要的
//        atomicInteger.lazySet(2333);
////    }catch(Exception e){
////         e.printStackTrace();
////        }
////     finally {
//        lock.unlock();
////    }
}
