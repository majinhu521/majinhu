package com.study.majinhu.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * @ClassName TimeTask
 * @Description
 *     原文链接：https://blog.csdn.net/J080624/article/details/96476640
 * @Author majinhu
 * @Date 2019/8/13 8:55
 * @Version 1.0
 **/
public class TimeTask {
    //schedule解释：1000ms是延迟启动时间，2000ms是定时任务周期，每2s执行一次
//    new Timer("testTimer").schedule(new TimerTask() {
//        @Override
//        public void run() {
//            System.out.println("TimerTask");
//        }
//    }, 1000,2000);
//
//    //解释：date是开始时间，2000ms是定时任务周期，每2s执行一次。
//    //Timer中的schedule和scheduleAtFixedRate，前者会等任务结束在开始计算时间间隔，
//    // 后者是在任务开始就计算时间，有并发的情况
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//    try {
//            Date date = dateFormat.parse("2019-07-11 12:00:00.000");
//            new Timer("testTimer1").scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    System.out.println("TimerTask");
//                }
//            }, date,2000);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

}
