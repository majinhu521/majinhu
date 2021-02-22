package com.study.majinhu.redis.bloomFilter;

/**
 * https://blog.csdn.net/qq_37334150/article/details/110432965
 *
 * <!--Redisson -->
 * 		<dependency>
 * 			<groupId>org.redisson</groupId>
 * 			<artifactId>redisson</artifactId>
 * 			<version>2.7.0</version>
 * 		</dependency>
 * @ClassName RedissonBloomFilter
 * @Description
 * @Author majinhu
 * @Date 2021/2/8 11:13
 * @Version 1.0
 **/
public class RedissonBloomFilter {
    public static void main(String[] args) {

//        Config config = new Config();
//        config.useSingleServer().setAddress("127.0.0.1:6379");
//        //config.useSingleServer().setPassword("");
//        //构造Redisson对象
//        RedissonClient redisson = Redisson.create(config);
//
//        //构造redis的布隆过滤器
//        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("redisBloomFilter");
//
//        //初始化布隆过滤器：预计元素为100000000L,误差率为0.03（google的默认值）
//        bloomFilter.tryInit(100000000L,0.03);
//
//        //插入测试数据到布隆过滤器中
//        bloomFilter.add("hello world");
//
//        //判断下面号码是否在布隆过滤器中
//        System.err.println(bloomFilter.contains("cbry"));    //false
//        System.err.println(bloomFilter.contains("hello world"));    //true
    }

}
