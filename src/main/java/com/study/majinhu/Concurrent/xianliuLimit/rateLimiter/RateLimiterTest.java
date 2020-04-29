package com.study.majinhu.Concurrent.xianliuLimit.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @ClassName RateLimiterTest
 * @Description 使用Guava RateLimiter限流以及源码解析
 * https://www.jianshu.com/p/5d4fe4b2a726
 * @Author majinhu
 * @Date 2019/12/4 9:29
 * @Version 1.0
 **/
public class RateLimiterTest {
    //Guava有两种限流模式，一种为稳定模式(SmoothBursty:令牌生成速度恒定)，
    // 一种为渐进模式(SmoothWarmingUp:令牌生成速度缓慢提升直到维持在一个稳定值) 两种模式实现思路类似，
    // 主要区别在等待时间的计算上，本篇重点介绍SmoothBursty
    //通过调用RateLimiter的create接口来创建实例，实际是调用的SmoothBuisty稳定模式创建的实例

    public void testAcquire() {
        RateLimiter limiter = RateLimiter.create(1);

        for(int i = 1; i < 10; i = i + 2 ) {
            double waitTime = limiter.acquire(i);
            System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
        }
    }

    public static void main(String[] args) {
        RateLimiterTest RateLimiterTest = new RateLimiterTest();
        RateLimiterTest.testAcquire();
    }
}
