package com.study.majinhu.Concurrent.ThreadPool;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * @Author majinhu
 * @Description  @Component 开启@Component，开启自动运行，去掉这个注解，不执行
 * @Date 10:57 2019/6/21
 * @Param
 * @return
 **/
//@Component
public class ScheduledThreadPoolExecutorRun implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=========== 项目启动后，初始化thread =============");
        /**
             * new timeTaskForException() 要执行的任务线程
             * 1000：延迟多长时间执行
             * 2000: 每隔多少长时间执行一次
             * TimeUnit.MILLISECONDS：时间单位
         */
        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(10);
        scheduledExecutor.scheduleAtFixedRate(new timeTaskForAppDb(), 1000, 2000, TimeUnit.MILLISECONDS);
        scheduledExecutor.scheduleAtFixedRate(new timeTaskForAppRedis(), 1000, 2000, TimeUnit.MILLISECONDS);
        scheduledExecutor.scheduleAtFixedRate(new timeTaskForAppApplication(), 1000, 2000, TimeUnit.MILLISECONDS);
        scheduledExecutor.scheduleAtFixedRate(new timeTaskForItpQrcode(), 1000, 2000, TimeUnit.MILLISECONDS);
        scheduledExecutor.scheduleAtFixedRate(new timeTaskForItpTvm(), 1000, 2000, TimeUnit.MILLISECONDS);
        scheduledExecutor.scheduleAtFixedRate(new timeTaskFoItpBom(), 1000, 2000, TimeUnit.MILLISECONDS);
        scheduledExecutor.scheduleAtFixedRate(new timeTaskForItpGateRake(), 1000, 2000, TimeUnit.MILLISECONDS);

        System.out.println("=========== 项目启动后，任务调度完成=============");
    }
    /**
     * 定時任務：app-数据库报警任务
     */
    static class  timeTaskForAppDb implements Runnable{

            public void run() {
                System.out.println("1.定时执行app-数据库报警任务  "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }

        }
    /**
     * 定時任務：app-redis报警任务
     */
        static class timeTaskForAppRedis implements Runnable {

            public void run() {
                System.out.println("2.定时执行app-redis报警任务  "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        }
    /**
     * 定時任務：app-应用报警任务
     */
    static class timeTaskForAppApplication implements Runnable {

        public void run() {
            System.out.println("3.定时执行app-应用报警任务  "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            boolean  stat2= false;
            try {
                  stat2 = TestConnect.testWsdlConnection("https://www.hahahc.com/");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("连接状态================"+stat2+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//状态
            if(!stat2){
                //发送短信服务
                //更新最大值，一天最多运行发送10条短信。
            }
        }
    }

    /**
     * 定時任務：ITP 二维码报警任务
     */
    static class timeTaskForItpQrcode implements Runnable {

        public void run() {
            System.out.println("4.定时执行ITP 二维码报警任务  "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }
    /**
     * 定時任務：ITP TVM报警任务
     */
    static class timeTaskForItpTvm implements Runnable {

        public void run() {
            System.out.println("5.定时执行ITP TVM报警任务  "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }
    /**
     * 定時任務：定时执行ITP BOM报警任务
     */
    static class timeTaskFoItpBom implements Runnable {

        public void run() {
            System.out.println("6.定时执行ITP BOM报警任务  "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }
    /**
     * 定時任務：ITP 闸机报警任务
     */
    static class timeTaskForItpGateRake implements Runnable {

        public void run() {
            System.out.println("7.定时执行ITP 闸机报警任务  "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }




}
