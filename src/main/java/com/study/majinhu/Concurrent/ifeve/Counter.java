package com.study.majinhu.Concurrent.ifeve;

/**
 * @ClassName Counter
 * @Description Java同步实例
 * http://ifeve.com/synchronized-blocks/
 * @Author majinhu
 * @Date 2019/12/24 16:51
 * @Version 1.0
 **/
public class Counter{

    long count = 0;
    public synchronized void add(long value){
        this.count += value;
    }
}

class CounterThread extends Thread{

    protected Counter counter = null;

    public CounterThread(Counter counter){
        this.counter = counter;

    }

    public void run() {

        for(int i=0; i<10; i++){

            counter.add(i);
        }
    }
}
 class Example {
    public static void main(String[] args){

        Counter counter = new Counter();

        Thread  threadA = new CounterThread(counter);

        Thread  threadB = new CounterThread(counter);

        threadA.start();

        threadB.start();

    }

}
