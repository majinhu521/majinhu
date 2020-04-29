package com.study.majinhu.Concurrent.xianliuLimit.redisLimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisLimiter
 * @Description redis实现网关限流(限制API调用次数1000次/分)
 * https://www.cnblogs.com/shuangyueliao/p/11595198.html
 * @Author majinhu
 * @Date 2019/12/4 9:45
 * @Version 1.0
 **/
public class RedisLimiter {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
//    RedisTemplate redisTemplate = new RedisTemplate();
//核心思路：用一个list来存放一串值，每次请求都把当前时间放进，
// 如果列表长度为1000，那么调用就是1000次。如果第1000次调用时的当前时间和最初的时间差小于60s，那么就是1分钟里调用超1000次。
// 否则，就清空列表之前的值
    public void limit1(){
        Long size = redisTemplate.opsForList().size("apiRequest");
        if (size < 1000) {
            redisTemplate.opsForList().leftPush("apiRequest", System.currentTimeMillis());
        } else {
            Long start = (Long) redisTemplate.opsForList().index("apiRequest", -1);
            if ((System.currentTimeMillis() - start) < 60000) {
                throw new RuntimeException("超过限流阈值");
            } else {
                redisTemplate.opsForList().leftPush("apiRequest", System.currentTimeMillis());
                redisTemplate.opsForList().trim("apiRequest", -1, -1);
            }
        }
    }

    //设置key，过期时间为1分钟，其值是api这分钟内调用次数
   // 对比：方法一耗内存，限流准确。方法二结果有部分误差，只限制key存在的这一分钟内调用次数低于1000次，不代表任意时间段的一分钟调用次数低于1000
    public void limit2(){
        Integer count = (Integer) redisTemplate.opsForValue().get("apiKey");
        Integer integer = Optional.ofNullable(count).orElse(0);
        if (integer > 1000) {
            throw new RuntimeException("超过限流阈值");
        }
        if (redisTemplate.getExpire("apiKey", TimeUnit.SECONDS).longValue() < 0) {
            redisTemplate.multi();
            redisTemplate.opsForValue().increment("apiKey", 1);
            redisTemplate.expire("apiKey", 60, TimeUnit.SECONDS);
            redisTemplate.exec();
        } else {
            redisTemplate.opsForValue().increment("apiKey", 1);
        }
    }
}
