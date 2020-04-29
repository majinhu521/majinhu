package com.study.majinhu.Concurrent.lock;

/**
 * @ClassName VolatileTest0
 * @Description https://www.cnblogs.com/soaringEveryday/p/4418604.html
 * 对于自增之类的非原子性操作，只能通过如下方式保证可见性：
 *
 * a. synchronized
 *
 * b. ReentrantLock
 *
 * c. AtomicInteger
 * @Author majinhu
 * @Date 2019/8/20 10:21
 * @Version 1.0
 **/
public class VolatileTest0 {
    public static int number = 0;

    public void increase(){
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        number++;
//        synchronized(this){
//            number++;
//        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i = 0 ; i < 500 ; i++){
            new Thread(new Runnable() {

                @Override
                public void run() {
                    test.increase();
                }
            }).start();
        }

//        //若当期依然有子线程没有执行完毕
//        while(Thread.activeCount() > 1){
//            Thread.yield();//使得当前线程（主线程）让出CPU时间片
//        }

        System.out.println("number is " + number);
    }
}
