package com.study.majinhu.Concurrent.countdownAndCyclic;

import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoneTest
 * @Description
 * 作用：Semaphone是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源。
 * 信号量可以理解为对锁的扩展，无论是内部锁synchronized还是重入锁ReentrantLock，一次只允许一个线程访问一个资源，
 * 而信号量却可以指定多个线程，同时访问同一个资源。
 *
 * Semaphore是一种基于计数的信号量。它可以设定一个阈值，基于此，多个线程竞争获取许可信号，
 * 做完自己的申请后归还，超过阈值后，线程申请许可信号将会被阻塞。Semaphore可以用来构建一些对象池，
 * 资源池之类的，比如数据库连接池，我们也可以创建计数为1的Semaphore，将其作为一种类似互斥锁的机制，
 * 这也叫二元信号量，表示两种互斥状态。
 * 原文：https://blog.csdn.net/lipeng_bigdata/article/details/52165426
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 *
 * 构造函数：
 *
 *  public Semaphore(int permits)
 * public Semaphore(int permits, boolean fair)
 * permits执行信号量的准入数，即同时能申请多少个许可。
 *
 * acquire()用来获取一个许可，若无许可能够获得，则会一直等待，知道获得许可。
 *
 * release()用来释放许可。注意在释放许可之前，必须先获得许可。
 * ---------------------
 * 作者：chenkaibsw
 * 来源：CSDN
 * 原文：https://blog.csdn.net/chenkaibsw/article/details/80937189
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 *
 *https://blog.csdn.net/hanchao5272/article/details/79780045
 * @Author majinhu
 * @Date 2019/7/17 9:12
 * @Version 1.0
 **/
public class SemaphoneTest {
    public static void main(String[] args) {
        int N = 8;            //工人数
        Semaphore semaphore = new Semaphore(5); //准入数
        for(int i=0;i<N;i++)
            new Worker(i,semaphore).start();
    }

    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();	//尝试获取一个准入的许可，若无法获取，就会线程等待
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();    //在线程访问结束后，释放一个许可
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
