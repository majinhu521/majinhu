package com.study.majinhu.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName SaticScheduleTask
 * @Description
 * 一、静态：基于注解
 * 二、动态：基于接口
 * 三、多线程定时任务
 *https://www.cnblogs.com/mmzs/p/10161936.html
 * https://blog.csdn.net/j080624/article/details/80959271
 * https://blog.csdn.net/J080624/article/details/80958081
 * https://blog.csdn.net/J080624/article/details/96476640
 * @Author majinhu
 * @Date 2019/7/22 14:45
 * @Version 1.0
 **/
//@Component
@Configuration //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class SaticScheduleTask {
//    Cron表达式参数分别表示：
//
//    秒（0~59） 例如0/5表示每5秒
//    分（0~59）
//    时（0~23）
//    日（0~31）的某天，需计算
//    月（0~11）
//    周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）

//    @Scheduled：除了支持灵活的参数表达式cron之外，还支持简单的延时操作，
//    例如 fixedDelay ，fixedRate 填写相应的毫秒数即可。
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void statictask(){
        System.err.println("执行静态定时任务时间: " +Thread.currentThread().getName()+ LocalDateTime.now());
    }

}
