package com.study.majinhu.asynchronousProgramming;

/**
 * @ClassName RunnableExample
 * @Description Runnable 多线程异步示例
 * 问题1：线程的创建和销毁有开销的，没有限制线程的个数，可能导致线程的耗尽。
 * 解决：使用线程池。
 * 问题2：上面使用 Thread 执行的异步任务并没有返回值，如果我们想异步执行 个任
 * 务，并且需要在任务执行完毕后获取任务执行结果，则上面这个方式是满足不了的
 *  解决：JDK 中的 Future
 * 问题3：每当需要异步执行时，我们需要显式创建线程并启动，这是典型的命令式编程方式，增加了编程者的心智负担
 * 解决：， Java 提供了很多封装良好 类库来解决
 *  doSomething b
 *  doSomething a
 * 2105
 * @Author majinhu
 * @Date 2020/11/11 9:15
 * @Version 1.0
 **/
public class ThreadExample   {

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
            Thread.sleep(1000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" doSomething b ");
    }
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        //1. 执行任务a
        Thread thread = new Thread("thread a"){
            @Override
            public void run() {
                doSomethingA();
            }
        };
        thread.start();
        //2. 执行任务b
        doSomethingB() ;
        try {
            //同步等待线程a结束
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out .println(System.currentTimeMillis() - start) ;
    }


}
