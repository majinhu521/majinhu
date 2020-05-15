package com.study.majinhu.redis.hongbao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.connection.jedis.JedisUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName GenRedPack
 * @Description 生成红包
 * @Author majinhu https://blog.csdn.net/tangdong3415/article/details/59486096
 * @Date 2020/5/15 15:08
 * @Version 1.0
 **/
@Service
public class GenRedPack {
    /**
     * 多线程模拟红包池初始化  Jedis类
     */
    public static void genHongBao() throws InterruptedException {
         Jedis jedis = new Jedis("127.0.0.1",6379);
//        final JedisUtils jedis = new JedisUtils(Basic.ip, Basic.port, Basic.auth);

        //发枪器
        final CountDownLatch latch = new CountDownLatch(Basic.threadCount);

        for(int i = 0 ; i < Basic.threadCount; i++){
            final int page = i;
            Thread thread = new Thread(){
                public void run(){
                    //每个线程要初始化多少个红包
                    int per = Basic.honBaoCount/Basic.threadCount;

                    JSONObject object = new JSONObject();

                    for(int j = page * per ; j < (page+1) * per; j++){ //从0开始，直到
                        object.put("id", "rid_"+j); //红包ID
                        object.put("money", j);   //红包金额
                        //lpush key value===lpush hongBaoPoolKey {id:rid_1, money:23}
                        jedis.lpush("hongBaoPoolKey", object.toJSONString());
                    }
                    latch.countDown(); //发枪器递减
                }
            };
            thread.start();
        }
        latch.await();//所有线程处于等状态
    }


}
