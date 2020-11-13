package com.study.majinhu.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisType
 * @Description https://blog.csdn.net/weixin_40461281/article/details/82011670
 * https://blog.csdn.net/bernkafly/article/details/89553711
 * Redis 可以存储键与5种不同数据结构类型之间的映射，
 * 这5种数据结构类型分别为String（字符串）、List（列表）、Set（集合）、Hash（散列）和 Zset（有序集合）。
 * @Author majinhu
 * @Date 2019/12/4 10:13
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisType {
    @Autowired
    RedisTemplate redisTemplate;

    //String
    @Test
    public void String(){
        //set void set(K key, V value);
        redisTemplate.opsForValue().set("num","123");
        redisTemplate.opsForValue().get("num");//输出结果为123

        //set void set(K key, V value, long timeout, TimeUnit unit);
        redisTemplate.opsForValue().set("num2","123",10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().get("num2");//设置的是10秒失效，十秒之内查询有结果，十秒之后返回为null

        //set void set(K key, V value, long offset);
        //覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始
        redisTemplate.opsForValue().set("key","hello world");
        redisTemplate.opsForValue().set("key","redis", 6);
        System.out.println("***************"+redisTemplate.opsForValue().get("key"));
        //结果：***************hello redis

        //get V get(Object key);
        redisTemplate.opsForValue().set("key","hello world");
        System.out.println("***************"+redisTemplate.opsForValue().get("key"));
        //结果：***************hello world

        //getAndSet V getAndSet(K key, V value); 
        //设置键的字符串值并返回其旧值

        redisTemplate.opsForValue().set("getSetTest","test");
        System.out.println(redisTemplate.opsForValue().getAndSet("getSetTest","test2"));
        //结果：test

        //append Integer append(K key, String value);
        //如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。
        redisTemplate.opsForValue().append("test","Hello");
        System.out.println(redisTemplate.opsForValue().get("test"));
        redisTemplate.opsForValue().append("test","world");
        System.out.println(redisTemplate.opsForValue().get("test"));
        //Hello
        //Helloworld

        //size Long size(K key);
        //返回key所对应的value值得长度
        redisTemplate.opsForValue().set("key","hello world");
        System.out.println("***************"+redisTemplate.opsForValue().size("key"));
        //***************11

    }

    //list
    @Test
    public void list() {
        //size
        System.out.println(redisTemplate.opsForList().size("list"));

        //Long leftPush(K key, V value);
        //将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从左边插入）
        redisTemplate.opsForList().leftPush("list","java");
        redisTemplate.opsForList().leftPush("list","python");
        redisTemplate.opsForList().leftPush("list","c++");
        //返回的结果为推送操作后的列表的长度
        //1
        //2
        //3

        //Long leftPushAll(K key, V... values);
        //批量把一个数组插入到列表中
        String[] strs = new String[]{"1","2","3"};
        redisTemplate.opsForList().leftPushAll("list",strs);
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        //[3, 2, 1]

        //Long rightPush(K key, V value);
        //将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从右边插入）
        redisTemplate.opsForList().rightPush("listRight","java");
        redisTemplate.opsForList().rightPush("listRight","python");
        redisTemplate.opsForList().rightPush("listRight","c++");
        //返回的结果为推送操作后的列表的长度
        //1
        //2
        //3

        //Long rightPushAll(K key, V... values);
        //批量把一个数组插入到列表中
        String[] strs2 = new String[]{"1","2","3"};
        redisTemplate.opsForList().rightPushAll("list",strs2);
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        //[3, 2, 1]

        //void set(K key, long index, V value);
        //在列表中index的位置设置value值
        System.out.println(redisTemplate.opsForList().range("listRight",0,-1));
        redisTemplate.opsForList().set("listRight",1,"setValue");
        System.out.println(redisTemplate.opsForList().range("listRight",0,-1));
        //[java, python, oc, c++]
        //[java, setValue, oc, c++]

//        Long remove(K key, long count, Object value);
//        从存储在键中的列表中删除等于值的元素的第一个计数事件。
//        计数参数以下列方式影响操作：
//        count> 0：删除等于从头到尾移动的值的元素。
//        count <0：删除等于从尾到头移动的值的元素。
//        count = 0：删除等于value的所有元素。
        System.out.println(redisTemplate.opsForList().range("listRight",0,-1));
        redisTemplate.opsForList().remove("listRight",1,"setValue");//将删除列表中存储的列表中第一次次出现的"setValue”。
        System.out.println(redisTemplate.opsForList().range("listRight",0,-1));
        //[java, setValue, oc, c++]
        //[java, oc, c++]

//        V index(K key, long index);
//        根据下表获取列表中的值，下标是从0开始的
        System.out.println(redisTemplate.opsForList().range("listRight",0,-1));
        System.out.println(redisTemplate.opsForList().index("listRight",2));
        //[java, oc, c++]
        //c++

//        V leftPop(K key);
//        弹出最左边的元素，弹出之后该值在列表中将不复存在
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        System.out.println(redisTemplate.opsForList().leftPop("list"));
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        //[c++, python, oc, java, c#, c#]
        // c++
        //[python, oc, java, c#, c#]

//        V rightPop(K key);
//        弹出最右边的元素，弹出之后该值在列表中将不复存在
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        System.out.println(redisTemplate.opsForList().rightPop("list"));
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        //[python, oc, java, c#, c#]
        // c#
        //[python, oc, java, c#]

    }

    //Redis的Hash数据机构
    @Test
    public void hash(){

        System.out.println(redisTemplate.opsForHash().delete("redisHash","name"));
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
        //1
        //{class=6, age=28.1}


        System.out.println(redisTemplate.opsForHash().hasKey("redisHash","666"));
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash","777"));
//        true
//        false

        System.out.println(redisTemplate.opsForHash().get("redisHash","age"));

        System.out.println(redisTemplate.opsForHash().keys("redisHash"));
        //redisHash所对应的散列表为{class=1, name=666, age=27}
        //[name, class, age]

        System.out.println(redisTemplate.opsForHash().size("redisHash"));
        //3

        Map<String,Object> testMap = new HashMap();
        testMap.put("name","666");
        testMap.put("age",27);
        testMap.put("class","1");
        redisTemplate.opsForHash().putAll("redisHash1",testMap);
        System.out.println(redisTemplate.opsForHash().entries("redisHash1"));
        // {class=1, name=jack, age=27}

        redisTemplate.opsForHash().put("redisHash","name","666");
        redisTemplate.opsForHash().put("redisHash","age",26);
        redisTemplate.opsForHash().put("redisHash","class","6");
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
        // {age=26, class=6, name=666}

        System.out.println(redisTemplate.opsForHash().values("redisHash"));
        //[tom, 26, 6]

        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
        //{age=26, class=6, name=tom}

//        Cursor<Map.Entry<HK, HV>> scan(H key, ScanOptions options);
//        使用Cursor在key的hash中迭代，相当于迭代器。
//        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash()
//                .scan("hashValue",ScanOptions.scanOptions().match("map1").build());
    Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan("hashValue",ScanOptions.NONE);
        while (cursor.hasNext()){
            Map.Entry<Object,Object> entry = cursor.next();
            System.out.println("通过scan(H key, ScanOptions options)方法获取匹配键值对:" + entry.getKey() + "---->" + entry.getValue());
        }
//        age:27
//        class:6
//        name:666
    }

    //Redis的Set数据结构
    @Test
    public void set(){
//        Long add(K key, V... values);
//        无序集合中添加元素，返回添加个数
//        也可以直接在add里面添加多个值 如：template.opsForSet().add("setTest","aaa","bbb")
        String[] strs= new String[]{"str1","str2"};
        System.out.println(redisTemplate.opsForSet().add("setTest", strs));
        //2

//        Long remove(K key, Object... values);
//        移除集合中一个或多个成员
        String[] strs2 = new String[]{"str1","str2"};
        System.out.println(redisTemplate.opsForSet().remove("setTest",strs2));
        //2

//        V pop(K key);
//        移除并返回集合中的一个随机元素
        System.out.println(redisTemplate.opsForSet().pop("setTest"));
        System.out.println(redisTemplate.opsForSet().members("setTest"));
//        bbb
//        [aaa, ccc]

//        Boolean move(K key, V value, K destKey);
//        将 member 元素从 source 集合移动到 destination 集合
        redisTemplate.opsForSet().move("setTest","aaa","setTest2");
        System.out.println(redisTemplate.opsForSet().members("setTest"));
        System.out.println(redisTemplate.opsForSet().members("setTest2"));
        //[ccc]
        //[aaa]


        System.out.println(redisTemplate.opsForSet().size("setTest"));

        System.out.println(redisTemplate.opsForSet().members("setTest"));
        //[ddd, bbb, aaa, ccc]

        Cursor<Object> curosr = redisTemplate.opsForSet().scan("setTest", ScanOptions.NONE);
        while(curosr.hasNext()){
            System.out.println(curosr.next());
        }
//        ddd
//        bbb
//        aaa
//        ccc
    }

//    Redis的ZSet数据结构
    @Test
    public void zset(){
        //Boolean add(K key, V value, double score);
        //新增一个有序集合，存在的话为false，不存在的话为true
        System.out.println(redisTemplate.opsForZSet().add("zset1","zset-1",1.0));
        //true

//        Long add(K key, Set<TypedTuple<V>> tuples);
//        新增一个有序集合
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-5",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-6",9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset1",tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        //[zset-1, zset-2, zset-3, zset-4, zset-5, zset-6]

//        Long remove(K key, Object... values);
//        从有序集合中移除一个或者多个元素
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        System.out.println(redisTemplate.opsForZSet().remove("zset1","zset-6"));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        //[zset-1, zset-2, zset-3, zset-4, zset-5, zset-6]
        //        1
        //[zset-1, zset-2, zset-3, zset-4, zset-5]


//        Long rank(K key, Object o);
//        返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        System.out.println(redisTemplate.opsForZSet().rank("zset1","zset-2"));
        //[zset-2, zset-1, zset-3, zset-4, zset-5]
        //0 //表明排名第一

        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        //[zset-2, zset-1, zset-3, zset-4, zset-5]

//        Long count(K key, double min, double max);
//        通过分数返回有序集合指定区间内的成员个数
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1",0,5));
        System.out.println(redisTemplate.opsForZSet().count("zset1",0,5));
        //[zset-2, zset-1, zset-3]
        // 3

//        Long size(K key);
//        获取有序集合的成员数，内部调用的就是zCard方法
        System.out.println(redisTemplate.opsForZSet().size("zset1"));
        //6

//        Double score(K key, Object o);
//        获取指定成员的score值
        System.out.println(redisTemplate.opsForZSet().score("zset1","zset-1"));
        //2.2

//        Long removeRange(K key, long start, long end);
//        移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().range("zset2",0,-1));
        System.out.println(redisTemplate.opsForZSet().removeRange("zset2",1,2));
        System.out.println(redisTemplate.opsForZSet().range("zset2",0,-1));
        //[zset-1, zset-2, zset-3, zset-4]
        //        2
        //  [zset-1, zset-4]


//        Cursor<TypedTuple<V>> scan(K key, ScanOptions options);
//        遍历zset
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan("zzset1", ScanOptions.NONE);
        while (cursor.hasNext()){
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            System.out.println(item.getValue() + ":" + item.getScore());
        }
//        zset-1:1.0
//        zset-2:2.0
//        zset-3:3.0
//        zset-4:6.0


    }

}
