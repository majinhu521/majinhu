package com.study.majinhu.redis.lock;

/**
 * @ClassName RedLockService
 * @Description
 * @Author majinhu
 * @Date 2021/2/8 13:39
 * @Version 1.0
 **/
public interface RedLockService {
    public String grabOrder(String orderId);
}
