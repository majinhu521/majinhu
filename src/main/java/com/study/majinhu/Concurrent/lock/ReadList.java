package com.study.majinhu.Concurrent.lock;

/**
 * @ClassName ReadList
 * @Description https://www.cnblogs.com/rinack/p/6888108.html
 * 可见读线程开始运行，开始wait过后，写线程才获得锁；写线程走出同步块而不是notify过后，
 * 读线程才wait结束，亦即获得锁。
 * 所以notify不会释放锁，wait会释放锁。值得一提的是，notifyall()会通知等待队列中的所有线程。
 * @Author majinhu
 * @Date 2019/8/20 9:20
 * @Version 1.0
 **/

import java.util.LinkedList;
import java.util.List;


class ReadList implements Runnable{

    private List list;

    public ReadList(List list){ this.list = list; }

    @Override
    public void run(){
        System.out.println("ReadList begin at "+System.currentTimeMillis());
        synchronized (list){
            try {
                Thread.sleep(1000);
                System.out.println("list.wait() begin at "+System.currentTimeMillis());
                list.wait();
                System.out.println("list.wait() end at "+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ReadList end at "+System.currentTimeMillis());

    }
}

class WriteList implements Runnable{

    private List list;

    public WriteList(List list){ this.list = list; }

    @Override
    public void run(){
        System.out.println("WriteList begin at "+System.currentTimeMillis());
        synchronized (list){
            System.out.println("get lock at "+System.currentTimeMillis());
            list.notify();
            System.out.println("list.notify() at "+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get out of block at "+System.currentTimeMillis());
        }
        System.out.println("WriteList end at "+System.currentTimeMillis());

    }


    public static void main(String[] args){
        List list = new LinkedList();
        Thread r = new Thread(new ReadList(list));
        Thread w = new Thread(new WriteList(list));
        r.start();
        w.start();
    }
}
