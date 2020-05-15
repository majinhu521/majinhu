package com.study.majinhu.redis.redistype;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName RedisTypeZSetUseTest
 * @Description
 * @Author majinhu
 * @Date 2020/5/15 11:23
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTypeZSetUseTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 在zset中添加a
     */
    @Test
    public void testZaddSet() {
        redisTemplate.opsForZSet().add("myZSet", "a", 10);
        System.out.println(redisTemplate.opsForZSet().range("myZSet", 0, -1));
    }

    /**
     * 在zset中批量添加数据
     */
    @Test
    public void testZaddsSet() {
        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>();
        DefaultTypedTuple<String> defaultTypedTuple1 = new DefaultTypedTuple<>("b", 20.0);
        DefaultTypedTuple<String> defaultTypedTuple2 = new DefaultTypedTuple<>("c", 30.0);
        DefaultTypedTuple<String> defaultTypedTuple3 = new DefaultTypedTuple<>("d", 40.0);
        set.add(defaultTypedTuple1);
        set.add(defaultTypedTuple2);
        set.add(defaultTypedTuple3);
        redisTemplate.opsForZSet().add("myZSet", set);
        System.out.println(redisTemplate.opsForZSet().range("myZSet", 0, -1));
    }

    /**
     * 获取zset中指定score范围值内的元素
     */
    @Test
    public void testZrangeByScoreSet() {
        System.out.println(redisTemplate.opsForZSet().rangeByScore("myZSet", 20.0, 40.0));
    }

    /**
     * 根据rank删除zset中的元素
     */
    @Test
    public void testZremSet() {
        redisTemplate.opsForZSet().remove("myZSet", "a");
        System.out.println(redisTemplate.opsForZSet().range("myZSet", 0, -1));
    }

    /**
     * 获取zset的长度
     */
    @Test
    public void testZcardSet() {
        System.out.println(redisTemplate.opsForZSet().size("myZSet"));
    }

    /**
     * 对zset中元素的score进行递增
     */
    @Test
    public void testZincrbySet() {
        System.out.println(redisTemplate.opsForZSet().score("myZSet", "c"));
        redisTemplate.opsForZSet().incrementScore("myZSet", "c", 10.0);
        System.out.println(redisTemplate.opsForZSet().score("myZSet", "c"));
    }

    /**
     * 根据score的区间值统计zset在改score区间中的元素个数
     */
    @Test
    public void testZcountSet() {
        System.out.println(redisTemplate.opsForZSet().count("myZSet", 20.0, 40.0));
    }

    /**
     * 根据score获取zset元素中rank
     */
    @Test
    public void testZrankSet() {
        System.out.println(redisTemplate.opsForZSet().rank("myZSet", "c"));
    }

    /**
     * 根据rank获取zset元素中score
     */
    @Test
    public void testZscoreSet() {
        System.out.println(redisTemplate.opsForZSet().score("myZSet", "c"));
    }
}