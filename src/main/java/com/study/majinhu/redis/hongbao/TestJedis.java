package com.study.majinhu.redis.hongbao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

/**
 * @ClassName TestJedis
 * @Description test
 * @Author majinhu
 * @Date 2020/5/15 16:15
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJedis {
    @Test
    public void testconn(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.set("testkey","majh");
        jedis.close();
    }

}
