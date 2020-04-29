package com.study.majinhu.Concurrent.xianliuLimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RateLimiterTest
 * @Description 令牌桶算法
 * https://blog.csdn.net/zc529739024/article/details/78744876
 * @Author majinhu
 * @Date 2019/6/25 9:19
 * @Version 1.0
 **/
public class RateLimiterTest {


    private void doCheck()   {
        /**
         * 创建一个限流器，设置每秒放置的令牌数：2个。速率是每秒可以2个的消息。
         * 返回的RateLimiter对象可以保证1秒内不会给超过2个令牌，并且是固定速率的放置。达到平滑输出的效果
         */
        RateLimiter r = RateLimiter.create(2);

        while(true)
        {
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
            //System.out.println(r.acquire());

            /**突发流量：如果要一次新处理更多的数据，则需要更多的令牌。代码首先获取2个令牌，
             * 那么下一个令牌就不是0.5秒之后获得了，还是1秒以后，之后又恢复常规速度。这是一个突发多的例子
             **/
//            System.out.println(r.acquire(2));
//            System.out.println(r.acquire(1));
//            System.out.println(r.acquire(1));
//            System.out.println(r.acquire(1));

            //等了两秒钟之后，令牌桶里面就积累了3个令牌，可以连续不花时间的获取出来。
            // 处理突发其实也就是在单位时间内输出恒定。
            // 这两种方式都是使用的RateLimiter的子类SmoothBursty。
            // 另一个子类是SmoothWarmingUp，它提供的有一定缓冲的流量输出方案。
//            System.out.println(r.acquire(1));
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(r.acquire(1));
//            System.out.println(r.acquire(1));
//            System.out.println(r.acquire(1));


            /**
             * 创建一个限流器，设置每秒放置的令牌数：2个。速率是每秒可以210的消息。
             * 返回的RateLimiter对象可以保证1秒内不会给超过2个令牌，并且是固定速率的放置。达到平滑输出的效果
             * 设置缓冲时间为3秒。
             * 由于设置了缓冲的时间是3秒，令牌桶一开始并不会0.5秒给一个消息，而是形成一个平滑线性下降的坡度，
             * 频率越来越高，在3秒钟之内达到原本设置的频率，以后就以固定的频率输出。
             * 图中红线圈出来的3次累加起来正好是3秒左右。这种功能适合系统刚启动需要一点时间来"热身”的场景。
             */
            RateLimiter r2 = RateLimiter.create(2,3,TimeUnit.SECONDS);

            while (true) {
                /**
                 * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
                 * acquire(N)可以获取多个令牌。
                 */
                System.out.println(r2.acquire(1));
                System.out.println(r2.acquire(1));
                System.out.println(r2.acquire(1));
                System.out.println(r2.acquire(1));
            }


        }
    }

    public static void main(String[] args)
    {
        RateLimiterTest counter = new RateLimiterTest();
        counter.doCheck();
    }


}




