package com.study.majinhu.redis.miaosha;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName BenchmarkTests
 * @Description
 * @Author majinhu
 * @Date 2019/8/28 9:34
 * @Version 1.0
 **/
public class BenchmarkTests {
    @Autowired
    MiaoshaService miaoshaService;

    @Test
    public void benchmark() throws IOException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2000);
        for (int i = 0; i <2000 ; i++) {
            String userId = "user_";//模拟同一用户
            String userId2 = "user_"+i;//模拟不同用户

            new Thread(() ->{
                    try {
                        cyclicBarrier.await();
                        miaoshaService.miaosha("10001",userId);
                    }catch (Exception e){ e.printStackTrace();;
                    }
                }).start();
        }
        System.in.read();

    }

}
