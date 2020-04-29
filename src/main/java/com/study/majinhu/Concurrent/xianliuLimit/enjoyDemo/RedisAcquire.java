package com.study.majinhu.Concurrent.xianliuLimit.enjoyDemo;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @ClassName RedisAcquire
 * @Description
 * @Author majinhu
 * @Date 2019/8/2 9:19
 * @Version 1.0
 **/
@Service
public class RedisAcquire {
    //引入redis的lua脚本支持
    private DefaultRedisScript<Long> getReidsScript;

    public  boolean acquire(String limitkey,int limit,int expire){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        getReidsScript = new DefaultRedisScript<>();
        getReidsScript.setResultType(Long.class);
        getReidsScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("ratelimiter.lua")));
        Long result = (Long)jedis.eval(getReidsScript.getScriptAsString(),1,limitkey,String.valueOf(limit),String.valueOf(expire));
        if(result== 0){ //返回值如果是0，被限制了。
            System.out.println("被限流了");
            return false;
        }
        //返回值如果不是0，不被限制了。
        return true;
    };

}
