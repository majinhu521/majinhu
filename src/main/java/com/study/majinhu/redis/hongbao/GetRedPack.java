package com.study.majinhu.redis.hongbao;

import org.springframework.data.redis.connection.jedis.JedisUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName GetRedPack
 * @Description 抢红包
 * @Author majinhu
 * @Date 2020/5/15 15:09
 * @Version 1.0
 **/
@Service
public class GetRedPack {
    //抢红包的方法
    static public void getHongBao() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(Basic.threadCount);//20 //发枪器
        for(int i = 0 ; i < Basic.threadCount ; i ++){  //20线程
            Thread thread = new Thread(){
                public void run(){
                    //拿到jedis连接
//                    JedisUtils jedis = new JedisUtils(Basic.ip, Basic.port, Basic.auth);
                     Jedis jedis = new Jedis("127.0.0.1",6379);
                    while(true){


                        //模拟一个用户ID使用UUID XXXX
                        String userid = UUID.randomUUID().toString();


                        //抢红包  eval 可以直接调用我们LUA脚本里的业务代码  object ={rid_1:1, money:9, userid:001}
                        Object object = jedis.eval(Basic.getHongBaoScript,4,Basic.hongBaoPoolKey,Basic.hongBaoDetailListKey,Basic.userIdRecordKey,userid);


                        if(null != object){
                            System.out.println("用户ID号："+userid+" 抢到红包的详情是 "+object);
                        }else{
                            if(jedis.llen(Basic.hongBaoPoolKey) == 0){
                                break;
                            }
                        }
                    }
                    latch.countDown();
                }
            };
            thread.start();

        }
        latch.await();
    }


}
