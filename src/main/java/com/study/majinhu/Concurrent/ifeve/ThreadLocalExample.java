package com.study.majinhu.Concurrent.ifeve;

/**
 * @ClassName ThreadLocalExample
 * @Description 线程隔离：ThreadLocal
 * 创建了两个线程共享一个MyRunnable实例。每个线程执行run()方法的时候，
 * 会给同一个ThreadLocal实例设置不同的值。如果调用set()方法的时候用synchronized关键字同步，
 * 而且不是一个ThreadLocal对象实例，那么第二个线程将会覆盖第一个线程所设置的值。
 * @Author majinhu
 * @Date 2019/12/24 16:59
 * @Version 1.0
 **/
public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =
                new ThreadLocal<Integer>();
        private Integer integer= null;
        @Override
        public void run() {
//            threadLocal.set( (int) (Math.random() * 100D) );
            synchronized(this){
                integer =(int) (Math.random() * 100D);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

//            System.out.println(threadLocal.get());
            System.out.println(integer);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }

}