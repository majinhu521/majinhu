package com.study.majinhu.redis.lock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
@Configuration
public class RedissionLockConfig {

    //红锁:
//    @Bean(name = "redissonRed1")
    @Primary
    public RedissonClient redissonRed1(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6379").setPassword("a123456").setDatabase(0);
        return Redisson.create(config);
    }
//    @Bean(name = "redissonRed2")
    public RedissonClient redissonRed2(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6380").setDatabase(0);
        return Redisson.create(config);
    }
//    @Bean(name = "redissonRed3")
    public RedissonClient redissonRed3(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6381").setDatabase(0);
        return Redisson.create(config);
    }
}
