package com.study.majinhu.Concurrent.ThreadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CrawlerRun
 * @Description https://www.cnblogs.com/junrong624/p/5504711.html
 * @Author majinhu
 * @Date 2019/6/19 17:11
 * @Version 1.0
 **/
public class CrawlerRun {
    //private static final Logger logger = LoggerFactory.getLogger(CrawlerRun.class);
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(8);//先定义8个线程空间
        final ScheduledFuture<?> future = service.scheduleAtFixedRate(new CrawlerTest(), 0,10,
                TimeUnit.SECONDS);
        //CrawlerTest()是一个实现Runnable接口的类，会自动运行里面的run()方法，0的意思就是启动等待时间，这里就是直接运行，
        //10是10分钟，要是想小时，就把TimeUnit.MINUTES换成 TimeUnit.HOURS

        //使用1个线程
        Thread t= new Thread(new Runnable() {
            public void run() {
                try {
                    future.get();
                } catch (Exception e) {
                    //logger.error(e.getCause().getMessage(), e.getCause());//日志信息输出
                    future.cancel(false);//关闭线程
                }
            }
        });
        t.start() ;

    }


    //如果想早上8点到晚上8点采集（期间还是10分钟跑一次），别的时间不采集，那就要在CrawlerTest()里面run()方法做判断
    static class CrawlerTest implements Runnable{
        public void run() {
            try {
                Calendar ca = Calendar.getInstance();
                int hour = ca.get(Calendar.HOUR_OF_DAY);//获取当时时间数
                if (hour < 8 || hour > 20) return ;
                System.out.println("早八点到晚20点执行：执行频率:10s");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        }


}
