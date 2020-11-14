package com.study.majinhu.asynchronousProgramming;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName FuturetaskTest
 * @Description
 * @Author majinhu
 * @Date 2020/11/14 17:49
 * @Version 1.0
 **/
public class FuturetaskTest {
    public static String doSomethingA() {
        try {

            Thread.sleep(2000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println(" doSomething a ");
        return "task A Return";
    }

    public static String doSomethingB() {
        try {
            Thread.sleep(2000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "task B Return";
    }
    //无返回值
    static class RunnableTaskA implements Runnable{
        @Override
        public void run() {
             doSomethingA();
        }
    }
    //有返回值
    static class CallableTaskA implements Callable<String>{
        @Override
        public String call() throws Exception {
            return doSomethingA();
        }
    }


    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();

        //匿名内部类的方式.lamd表达式.
//       FutureTask<String> futureTask = new FutureTask<String>(()->{
//                String  result  =  null ;
//                try  {
//                    result  =  doSomethingA() ;
//                }  catch  (Exception  e) {
//                    e.printStackTrace();
//                }
//                    return  result ;
//
//        });

        //返回任务A对应的结果.内部类.
//        FutureTask<String> futureTask = new FutureTask<String>(new CallableTaskA());

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return doSomethingA();
            }
        };
        //返回任务A对应的结果.本地变量.
        FutureTask<String> futureTask = new FutureTask<String>(callable);

            //返回固定结果,或者null空结果
//        FutureTask<String> futureTask = new FutureTask<String>(new RunnableTaskA(),"a is over done");

        Thread  thread  =  new  Thread(futureTask,  "threadA" ) ;
        thread . start  ( ) ;

        String  taskBResult  =  doSomethingB() ;

        String  taskAResult  =  futureTask.get() ;

        System . out . println(taskAResult  + " " +  taskBResult) ;
        System . out . println(System . currentTimeMillis()  - start) ;
}
}