package com.study.majinhu.redis.lock;

import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/xiaozhu0301/article/details/111467348
 * <dependency>
 *    <groupId>org.redisson</groupId>
 *    <artifactId>redisson</artifactId>
 *    <version>3.9.0</version>
 * </dependency>
 * @ClassName RedissionLockTest
 * @Description
 * @Author majinhu
 * @Date 2021/2/8 11:22
 * @Version 1.0
 **/
public class RedissionLockTest {


    // 红锁
    @Autowired
    @Qualifier("redissonRed1")
    private RedissonClient redissonRed1;

    @Autowired
    @Qualifier("redissonRed2")
    private RedissonClient redissonRed2;

    @Autowired
    @Qualifier("redissonRed3")
    private RedissonClient redissonRed3;
    /**
     * 获取多个 RLock 对象
     */
    String lockKey = "lock_red";

    RLock lock1 = redissonRed1.getLock(lockKey);
    RLock lock2 = redissonRed2.getLock(lockKey);
    RLock lock3 = redissonRed3.getLock(lockKey);

    RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);
    /**
     * 根据多个 RLock 对象构建 RedissonRedLock （最核心的差别就在这里）
     */

    public  void trylock(long waitTimeout,long leaseTime){

        try {
            /**
             * 4.尝试获取锁
             * waitTimeout 尝试获取锁的最大等待时间，超过这个值，则认为获取锁失败
             * leaseTime   锁的持有时间,超过这个时间锁会自动失效（值应设置为大于业务处理的时间，确保在锁有效期内业务能处理完）
             */
            boolean res = redLock.tryLock((long)waitTimeout, (long)leaseTime, TimeUnit.SECONDS);
            if (res) {
                //成功获得锁，在这里处理业务
            }
        } catch (Exception e) {
            throw new RuntimeException("aquire lock fail");
        }finally{
            //无论如何, 最后都要解锁
            redLock.unlock();
        }
    }


}
