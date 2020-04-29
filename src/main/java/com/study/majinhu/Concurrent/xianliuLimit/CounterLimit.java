package com.study.majinhu.Concurrent.xianliuLimit;

import java.util.LinkedList;

/**
 * @ClassName CounterLimit
 * @Description 计数器限流
 *  https://blog.csdn.net/zc529739024/article/details/78744876
 * @Author majinhu
 * @Date 2019/6/25 8:46
 * @Version 1.0
 **/
public class CounterLimit{
    //服务访问次数，可以放在Redis中，实现分布式系统的访问计数
     Long counter = 0L;
    //使用LinkedList来记录滑动窗口的10个格子。
    LinkedList<Long> ll = new LinkedList<Long>();

    private void doCheck()   {
        while (true)
        {
            ll.addLast(counter);
            if (ll.size() > 10)
            {
                ll.removeFirst();
                System.out.println(">10,removeFirst");
            }else{
                System.out.println("<10,do not removeFirst");
            }

            //比较最后一个和第一个，两者相差一秒
            if ((ll.peekLast() - ll.peekFirst()) > 100)
            {
                //To limit rate
                System.out.println("To limit rate >100");
            }else{
                System.out.println("To limit rate <100");
            }
            try {
                Thread.sleep(1000);
                System.out.println("sleep 100");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

public static void main(String[] args)
        {
            CounterLimit counter = new CounterLimit();
            counter.doCheck();
        }


 }