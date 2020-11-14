package com.study.majinhu.asynchronousProgramming;

import java.util.concurrent.*;

/**
 * @ClassName FuturetaskTest
 * @Description
 * @Author majinhu
 * @Date 2020/11/14 17:49
 * @Version 1.0
 **/
public class FuturetaskThreadPOOLTest {

    // 自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS ,AVALIABLE_PROCESSORS * 2 ,
                    1 , TimeUnit.MINUTES, new LinkedBlockingQueue<>(5) ,
                    new ThreadPoolExecutor .CallerRunsPolicy() ) ;

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
        //返回任务A对应的结果.内部类.
//        FutureTask<String> futureTask = new FutureTask<String>(new CallableTaskA());
//
//        POOL_EXECUTOR.execute(futureTask);

       Future<String> futureTask = POOL_EXECUTOR.submit(()->{
                String  result  =  null ;
                try  {
                    result  =  doSomethingA() ;
                }  catch  (Exception  e) {
                    e.printStackTrace();
                }
                    return  result ;

        });

        String  taskBResult  =  doSomethingB() ;
        String  taskAResult  =  futureTask.get() ;
        System . out . println(taskAResult  + " " +  taskBResult) ;
        System . out . println(System . currentTimeMillis()  - start) ;
        POOL_EXECUTOR.shutdown();
}
}