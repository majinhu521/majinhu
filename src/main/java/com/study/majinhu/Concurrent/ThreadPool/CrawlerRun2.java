package com.study.majinhu.Concurrent.ThreadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CrawlerRun2
 * @Description  如果想指定就8点，12点，16点，20点采集，别的时间都不采集，那就要加判断
 * @Author majinhu
 * @Date 2019/6/19 17:21
 * @Version 1.0
 **/
public class CrawlerRun2 {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerRun.class);

    private static long delayTime(long currentTime , String campareDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date() ;

        //String str = TimeFormat.getYearMonthDate();
        String str = Calendar.getInstance().getTime().toString();
        str = str.replaceAll("00:00:00", campareDateStr) ;
        try {
            date = sdf.parse(str) ;
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        long dateMillis = date.getTime() ;

        return dateMillis - currentTime ;


    }
    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis() ;
        long realDelayTime = 0 ;
        long delayTime = CrawlerRun2.delayTime(currentTime, "08:00:00") ;
        long delayTime2= CrawlerRun2.delayTime(currentTime, "12:00:00") ;
        long delayTime3= CrawlerRun2.delayTime(currentTime, "16:00:00") ;
        long delayTime4= CrawlerRun2.delayTime(currentTime, "20:00:00") ;

        List<Long> list = new ArrayList<Long>() ;

        list.add(delayTime) ;
        list.add(delayTime2) ;
        list.add(delayTime3) ;
        list.add(delayTime4) ;

        Collections.sort(list);


        for (Long time : list) {
            if(time < 0 ) continue ;
            else{
                realDelayTime = time;
                break;
            }
        }

        ScheduledExecutorService service = Executors.newScheduledThreadPool(8);//先定义8个线程空间
        final ScheduledFuture<?> future = service.scheduleAtFixedRate(new CrawlerTest(), realDelayTime/1000, 4*60*60,
                TimeUnit.SECONDS);

        //使用1个线程
        Thread t= new Thread(new Runnable() {
            public void run() {
                try {
                    future.get();
                } catch (Exception e) {
                    logger.error(e.getCause().getMessage(), e.getCause());//日志信息输出
                    future.cancel(false);//关闭线程
                }
            }
        });
        t.start() ;

    }

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
