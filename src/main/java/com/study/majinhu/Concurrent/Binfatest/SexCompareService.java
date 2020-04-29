package com.study.majinhu.Concurrent.Binfatest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @ClassName SexCompareService
 * @Description 性能优化：用户服务，调用远程服务；并行调用远程服务。
 * @Author majinhu
 * @Date 2019/6/19 15:54
 * @Version 1.0
 **/
@Service
public class SexCompareService {
    @Autowired
    RemoteService remoteService;

    //newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
    //newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    //newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
    //newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

    //线程池优化
    ExecutorService POOLS = Executors.newFixedThreadPool(2);
    //无界线程池
    ExecutorService executorService= Executors.newCachedThreadPool();


    public String getUserInfo(String userId){

        long start = System.currentTimeMillis();

//        Callable<JSONObject> cal1 = new Callable<JSONObject>() {
//            @Override
//            public JSONObject call() throws Exception {
//                String V1 = remoteService.getUserInfo(userId);
//                JSONObject userInfo = JSONObject.parseObject(V1);
//                return userInfo;
//            }
//        } ;
//        Callable<JSONObject> cal2 = new Callable<JSONObject>() {
//            @Override
//            public JSONObject call() throws Exception {
//                String V2 = remoteService.getUserMoney(userId);
//                JSONObject userMoney = JSONObject.parseObject(V2);
//                return userMoney;
//            }
//        } ;
//        FutureTask<JSONObject> usertask = new FutureTask<JSONObject>(cal1);
//        FutureTask<JSONObject> moneytask = new FutureTask<JSONObject>(cal2);
//        //new Thread(usertask).start();
//        //new Thread(moneytask).start();
//        POOLS.submit(usertask);
//        POOLS.submit(moneytask);

       //方式二：连写
        Future<JSONObject> usertask = executorService.submit(new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                String V1 = remoteService.getUserInfo(userId);
                JSONObject userInfo = JSONObject.parseObject(V1);
                return userInfo;
            }
        });

        Future<JSONObject> moneytask = executorService.submit(new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                String V2 = remoteService.getUserMoney(userId);
                JSONObject userMoney = JSONObject.parseObject(V2);
                return userMoney;
            }
        });

        JSONObject result = new JSONObject();
        try {
            result.putAll(usertask.get());
            result.putAll(moneytask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println( "执行总时间为"+(System.currentTimeMillis()-start));
        System.out.println( "执行结果："+result.toString());
        return result.toString();
    }

}
