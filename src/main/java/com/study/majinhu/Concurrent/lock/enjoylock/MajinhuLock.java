package com.study.majinhu.Concurrent.lock.enjoylock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName MajinhuLock
 * @Description 手写锁，参照ReentrantLock实现自己的锁
 * 实现lock的接口，重新lock和unlock方法。
 * @Author majinhu
 * @Date 2020/5/21 9:30
 * @Version 1.0
 **/
public class MajinhuLock implements Lock {
    //创建一个引用对象的原子对象类。把线程对象放进去。
    AtomicReference<Thread> owner = new AtomicReference<Thread>();
    //创建一个阻塞队列来存放对应的线程。
    //使用队列。放阻塞的线程。确保线程安全。
    //Linkedblockingqueue 。Linked比array增加和删除效率更高。 排队。
    //抢不到，把当前线程放进去。进行阻塞。不能用wait，需要和syc配合使用
    LinkedBlockingQueue block = new LinkedBlockingQueue();

    @Override
    public void lock() {
        //如果线程是空的，把自己放进去，期望空值，交换自己这个线程。
        //如果没有获取奥锁，进行阻塞等待。直到有线程唤醒。
       //if(!owner.compareAndSet(null,owner)){ //错误，不是自己把自己放
        while(!owner.compareAndSet(null,Thread.currentThread())){
            block.add(Thread.currentThread());
            LockSupport.park();//阻塞，进入停车场。
            block.remove(Thread.currentThread());//优雅的移除。必须有别的线程来了才会执行到这一步。把自己移除。
        }
    }
    @Override
    public void unlock() {
        //只有持有锁的才能解锁。期望自己这个对象，更新为空。
        if(owner.compareAndSet(Thread.currentThread(),null)){
            //遍历阻塞队列。
            for (Object obj : block.toArray()) {
                Thread next = (Thread) obj;//取出线程。
                LockSupport.unpark(next);//挨个唤醒。
            }

        }

    }
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }
}
