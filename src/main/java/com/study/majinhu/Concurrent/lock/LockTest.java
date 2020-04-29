package com.study.majinhu.Concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockTest
 * @Description
 * @Author majinhu
 * @Date 2019/8/20 9:42
 * @Version 1.0
 **/
public class LockTest {

    //Lock是一个接口
    private Lock lock = new ReentrantLock();
    private int count;
    public int increment(){
        //加锁
        lock.lock();
        try {
            count++;
        }finally {
            //必须手动释放锁
            lock.unlock();
        }
        return count;
    }

    public static void main(String[] args) {
        LockTest lc = new LockTest();
        for (int i = 0; i <100 ; i++) {
            System.out.println(lc.increment());
        }

    }
}
