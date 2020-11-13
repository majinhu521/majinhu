package com.study.majinhu.asynchronousProgramming;

/**
 * @ClassName SyncExample
 * @Description 同步示例：执行时间为a+b的方法之和。
 * 异步并发编程 一书。
 *  doSomething a
 *  doSomething a
 * 4008
 * @Author majinhu
 * @Date 2020/11/11 9:07
 * @Version 1.0
 **/
public class SyncExample {

    public static void doSomethingA() {
        try {
                Thread.sleep(2000) ;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" doSomething a ");
        }

    public static void doSomethingB() {
        try {
            Thread.sleep(2000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" doSomething b ");
    }
   public static void main(String[] args){
       long start = System.currentTimeMillis();
       //1. 执行任务
       doSomethingA() ;
       //2. 执行任务
       doSomethingB() ;
       System.out .println(System.currentTimeMillis() - start) ;
   }
}


