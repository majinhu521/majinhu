package com.study.majinhu.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedPacketRedis
 * @Description
 * @Author majinhu
 * @Date 2019/8/8 15:00
 * @Version 1.0
 **/
@Component
public class RedPacketRedis {

    Logger logger=Logger.getLogger(RedPacketRedis.class);
    @Autowired
    private StringRedisTemplate redisTemplate;
    private static final String RED_PACKET ="RED_PACKET_";
    /**
     * 尝试获取全局锁
     *
     * @param param 红包的key
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(String param) {
        String key = RED_PACKET + param;
        logger.info("全局锁key:"+key);
        return getLock(key,2);
    }


    /**
     * 操作redis获取全局锁
     *
     * @param key            锁的名称
     * @return true 获取成功，false获取失败
     */
    public boolean getLock(String key,long lockExpireTime) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        if (redisTemplate.opsForValue().setIfAbsent(key, key)) {
            redisTemplate.opsForValue().set(key, key,lockExpireTime, TimeUnit.SECONDS);
            logger.info(key + " : ----> get lock");
            return true;
        } else {
            logger.info(key + " : ----> locking is exist!!! 线程："+Thread.currentThread().getName());
            return false;
        }
    }
    public void unLock(String param) {
        String key = RED_PACKET + param;
        redisTemplate.opsForValue().getOperations().delete(key);
    }

}
