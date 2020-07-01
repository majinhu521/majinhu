package com.study.majinhu.Concurrent.manyThead.twowaythread;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alipay.api.domain.AlipayBossBaseProcessSignVerifyModel;

/**
 * @ClassName TwoWayThread
 * @Description 2种线程方式
 * @Author majinhu
 * @Date 2020/6/15 16:20
 * @Version 1.0
 **/
public class TwoWayThread {

    private static class UseThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("do my work，i am extends Thread!");
        }
    }
    private static class UseRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("do my work，i am implements Runnable!");
        }
    }
    public static void main(String[] args){
        UseThread ut = new UseThread();
        ut.start();
        UseRunnable ur = new UseRunnable();
        Thread thread = new Thread(ur);
        thread.start();
    }
}
