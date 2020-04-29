package com.study.majinhu.Concurrent.safe;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName DeadLock
 * @Description 死锁模拟
 *   1.m个人争夺n个资源。M >=n ;m>=2,n>=2;
 *   2.争夺的顺序不对。（互斥）
 *   3.2边都不释放资源。
 *
 *   破坏死锁的条件，后2个。
 *    jdk 查询死锁
 *   C:\Program Files\Java\jdk1.8.0_121\bin>jps
 * 5664
 * 8560 RemoteMavenServer
 * 9200 DeadLock
 * 7524 Jps
 * 7324 Launcher
 *
 * C:\Program Files\Java\jdk1.8.0_121\bin>jstack 9200
 * @Author majinhu
 * @Date 2020/4/10 15:02
 * @Version 1.0
 **/
public class DeadLock2 {

    private static Lock N13 = new ReentrantLock();
    private static Lock N14 = new ReentrantLock();

    private static void jamesdo()  throws  Exception {
        String threadName = Thread.currentThread().getName();
        Random r = new Random();
        while (true) {
            if (N13.tryLock()) {
                try {
                    System.out.println(threadName + "get N13");
                    if (N14.tryLock()) {
                        try {
                            System.out.println(threadName + "get N14");
                            break;
                        } finally {
                            N14.unlock();
                        }
                    }
                } finally {
                    N13.unlock();
                }
            }
            Thread.sleep(r.nextInt(3));
        }
    }

    private static void lisondo() throws  Exception{
        String threadName = Thread.currentThread().getName();
        Random r = new Random();
        while (true) {
            if (N14.tryLock()) {
                try {
                    System.out.println(threadName + "get N14");
                    if (N13.tryLock()) {
                        try {
                            System.out.println(threadName + "get N13");
                            break;
                        } finally {
                            N13.unlock();
                        }
                    }
                } finally {
                    N14.unlock();
                }
            }
            Thread.sleep(r.nextInt(3));
        }
    }

    private static class James extends Thread {
        private String name;
        public James(String name){
            this.name = name;
        }
        @Override
        public  void run(){
            Thread.currentThread().setName(name);
            try {
                jamesdo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws  Exception {
        Thread.currentThread().setName("Lison");
        James james = new James("james");
        james.start();
        lisondo();
    }
}
