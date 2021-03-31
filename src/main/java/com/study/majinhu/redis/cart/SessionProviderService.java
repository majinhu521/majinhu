package com.study.majinhu.redis.cart;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName SessionProviderService
 * @Description
 * @Author majinhu
 * @Date 2021/3/15 11:08
 * @Version 1.0
 **/
@Component
public class SessionProviderService {
    @Resource
    private JedisUtils jedis;

    private int exp =20;

    public String getAttributterForUsername(String jessionId){
                 String value = jedis.get(jessionId + ":USER_NAME");
                if(null != value){
                         //计算session过期时间是 用户最后一次请求开始计时.
                         jedis.expire(jessionId + ":USER_NAME", 60*exp);
                         return value;
                     }
                 return null;
             }
}
