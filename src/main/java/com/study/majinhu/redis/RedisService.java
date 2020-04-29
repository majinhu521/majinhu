package com.study.majinhu.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @ClassName RedisService
 * @Description
 * https://segmentfault.com/a/1190000011421467
 * 锁的value值是当前时间加上过期时间的时间戳，Long类型。
 * 首先看到用setiFAbsent方法也就是对应的SETNX，在没有线程获得锁的情况下可以直接拿到锁，
 * 并返回true也就是加锁，最后没有获得锁的线程会返回false。
 * 最重要的是中间对于锁超时的处理，如果没有这段代码，当秒杀方法发生异常的时候，
 * 后续的线程都无法得到锁，也就陷入了一个死锁的情况。我们可以假设CurrentValue为A，
 * 并且在执行过程中抛出了异常，这时进入了两个value为B的线程来争夺这个锁，也就是走到了注释*的地方。
 * currentValue==A，这时某一个线程执行到了getAndSet(key,value)函数
 * (某一时刻一定只有一个线程执行这个方法，其他要等待)。这时oldvalue也就是之前的value等于A，
 * 在方法执行过后，oldvalue会被设置为当前的value也就是B。这时继续执行，
 * 由于oldValue==currentValue所以该线程获取到锁。
 * 而另一个线程获取的oldvalue是B，而currentValue是A，所以他就获取不到锁啦。
 *
 * 参照：https://www.jb51.net/article/178898.htm
 *
 * @Author majinhu
 * @Date 2019/8/9 8:59
 * @Version 1.0
 **/
@Slf4j
@Component
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /***
     * 加锁
     * @param key
     * @param value 当前时间+超时时间
     * @return 锁住返回true
     */
    public boolean lock(String key,String value){
        if(stringRedisTemplate.opsForValue().setIfAbsent(key,value)){//setNX 返回boolean
            return true;
        }
        //如果锁超时 ***
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue)<System.currentTimeMillis()){
            //获取上一个锁的时间
            String oldvalue  = stringRedisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldvalue)&&oldvalue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }
    /***
     * 解锁
     * @param key
     * @param value
     * @return
     */
    public void unlock(String key,String value){
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("解锁异常");
        }
    }
}
