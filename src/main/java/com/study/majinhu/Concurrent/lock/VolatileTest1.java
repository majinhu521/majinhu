package com.study.majinhu.Concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName VolatileTest1
 * @Description
 * @Author majinhu
 * @Date 2019/8/20 10:15
 * @Version 1.0
 **/
public class VolatileTest1 {
    public static int number = 0;
    public Lock lock =  new ReentrantLock();

    public void increase(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        lock.lock();

        try{
            number++;//这块的代码实际项目中可能会出现异常，所以要捕获
        }finally{
            lock.unlock();//用try finally块保证Unlock一定要执行
        }


    }
}
