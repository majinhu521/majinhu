package com.study.majinhu.Concurrent.safe;

/**
 * @ClassName SimpleOrder
 * @Description 2个线程相加
 * @Author majinhu
 * @Date 2020/4/10 14:45
 * @Version 1.0
 **/
public class SimpleOrder {
    private volatile long count =0;
    //方法一：不加关键字，不会相等，并发安全问题。
//    public  void countAdd(){
//        count = count+1;
//    }
    //方法二：synchronized 关键字
    public synchronized void countAdd(){
        count = count+1;
    }

    private static class Count extends Thread{
        private SimpleOrder simpleOrder;

        public Count(SimpleOrder simpleOrder) {
            this.simpleOrder = simpleOrder;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                simpleOrder.countAdd();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SimpleOrder simpleOrder = new SimpleOrder();
        Count count1 = new Count(simpleOrder);
        Count count2 = new Count(simpleOrder);
        count1.start();
        count2.start();
        Thread.sleep(50);
        System.out.println(simpleOrder.count);
    }
}
