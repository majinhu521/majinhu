package com.study.majinhu.redis;

import com.study.majinhu.MajinhuApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName TestRedisLock
 * @Description 测试锁
 * @Author majinhu
 * @Date 2019/8/9 9:06
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MajinhuApplication.class)
public class TestRedisLock {

    @Autowired
    RedPacketRedis redPacketRedis;

    @Test
    public void runTest() {
        try {
            System.out.println("获取锁===============");
            lockAndSleep("124910");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行业务===============");
        //redPacketRedis.unLock("124910");
        System.out.println("释放锁===============");
    }



    private  void lockAndSleep(String ownerId) throws InterruptedException {
        if (!redPacketRedis.tryLock(ownerId)) {
            Thread.sleep(200);
            lockAndSleep(ownerId);
        }
    }
}

