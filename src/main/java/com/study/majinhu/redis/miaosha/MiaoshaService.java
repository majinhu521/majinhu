package com.study.majinhu.redis.miaosha;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @ClassName miaoshaService
 * @Description 秒杀业务，使用redis实现限流。
 * @Author majinhu
 * @Date 2019/8/28 9:35
 * @Version 1.0
 **/
//@Service
public class MiaoshaService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public Boolean miaosha(String goodsCode,String userId){
        //同1用户 10s 之内不能重复提交。
        Boolean r = stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                //10s内插入userId这个key，如果插入成功则true
                //set key value ex nx
                Boolean success = redisConnection.set(userId.getBytes(), "123".getBytes(), Expiration.seconds(10), RedisStringCommands.SetOption.SET_IF_ABSENT);
                return success;
            }
        });
        //插入失败，则这个key已经被占用了。
        if(!r){
            System.out.println("10s之内已经请求过"+userId);
            return false;
        }

        //令牌桶限流，真实用户限流。
        String token = stringRedisTemplate.opsForList().leftPop("token_list_" + goodsCode);
        if(token ==null || "".equals(token)){
            System.out.println("没有拿到token，被限流"+userId);
            return false;
        }

        Boolean result = buy(goodsCode,userId);//购买，减库存，
        //参与秒杀业务
        System.out.println("秒杀结果"+result);
        return result;

    }

    Boolean buy(String goodsCode,String userId){
        if(goodsCode.equals("10001")){//执行数据库操作。
            System.out.println("秒杀商品"+goodsCode+"，userId"+userId);
            return true;
        }else{
            System.out.println("秒杀商品不存在"+goodsCode+"，userId"+userId);
            return false;
        }
    }
    //初始化令牌池，提前加载。
    @PostConstruct
    public void initToken(){
        for (int i = 0; i <120 ; i++) {
            String goodsCode = "10001";
            stringRedisTemplate.opsForList().leftPush("token_list_"+goodsCode,String.valueOf(i));
            System.out.println("令牌池初始化完毕：120");//库存100，初始化120个。
        }
    }

}
