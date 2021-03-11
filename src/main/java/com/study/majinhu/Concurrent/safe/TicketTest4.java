package com.study.majinhu.Concurrent.safe;


/**
 * @ClassName TicketTest
 * @Description 线程安全 三种方式
 * https://www.cnblogs.com/lizhangyong/p/8029287.html
 *最后总结：
 *
 * 　　由于synchronized是在JVM层面实现的，因此系统可以监控锁的释放与否；
 * 而ReentrantLock是使用代码实现的，系统无法自动释放锁，
 * 需要在代码中的finally子句中显式释放锁lock.unlock()。
 *
 * 　另外，在并发量比较小的情况下，使用synchronized是个不错的选择；
 * 但是在并发量比较高的情况下，其性能下降会很严重，此时ReentrantLock是个不错的方案。

 * 　　补充：　　
 *
 * 　　在使用synchronized 代码块时,可以与wait()、notify()、notifyAll()一起使用，从而进一步实现线程的通信。
 * 其中，wait()方法会释放占有的对象锁，当前线程进入等待池，释放cpu,而其他正在等待的线程即可抢占此锁，获得锁的线程即可运行程序；
 * 线程的sleep()方法则表示，当前线程会休眠一段时间，休眠期间，会暂时释放cpu，但并不释放对象锁，
 * 也就是说，在休眠期间，其他线程依然无法进入被同步保护的代码内部，当前线程休眠结束时，会重新获得cpu执行权,从而执行被同步保护的代码。
 * wait()和sleep()最大的不同在于wait()会释放对象锁，而sleep()不会释放对象锁。
 *
 * notify()方法会唤醒因为调用对象的wait()而处于等待状态的线程，从而使得该线程有机会获取对象锁。
 * 调用notify()后，当前线程并不会立即释放锁，而是继续执行当前代码，直到synchronized中的代码全部执行完毕，才会释放对象锁。
 * JVM会在等待的线程中调度一个线程去获得对象锁，执行代码。
 *
 * 　　需要注意的是，wait()和notify()必须在synchronized代码块中调用。
 *
 * 　　notifyAll()是唤醒所有等待的线程。
 *
 * 　　接下来，我们通过下一个程序，使得两个线程交替打印“A”和“B”各10次。
 * @Author majinhu
 * @Date 2021/3/11 15:21
 * @Version 1.0
 **/
public class TicketTest4 {
    static final Object obj=new Object();
    static int count=10;
    //一个子线程
    static class ThreadA implements Runnable{
        @Override
        public void run() {
            //int count=10;
            while(count>0){
                synchronized(TicketTest4.obj){
                    System.out.println("A-->"+count);
                    count--;
                    TicketTest4.obj.notify();
                    try {
                        TicketTest4.obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    TicketTest4.obj.notify();//必须，否则无法停止程序。
                }
            }
        }
    }
    //Wait：当前线程进入阻塞队列，让出执行权。
    //Notify：随机叫醒一个其他线程。
    //Notfyall：全部叫醒

    //另一个子线程
    static class ThreadB implements Runnable{
        @Override
        public void run() {
            //int count=10;
            while(count>0){
                synchronized(TicketTest4.obj){
                    System.out.println("B-->"+count);
                    count--;
                    TicketTest4.obj.notify();
                    try {
                        TicketTest4.obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    TicketTest4.obj.notify();
                }
            }
        }
    }
    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }
}
