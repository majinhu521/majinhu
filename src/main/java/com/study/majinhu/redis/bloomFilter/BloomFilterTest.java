package com.study.majinhu.redis.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BloomFilterTest
 * @Description
 * 布隆过滤器的方式解决缓存穿透问题
 * 1、原理
 * 布隆过滤器的巨大用处就是，能够迅速判断一个元素是否在一个集合中。因此他有如下三个使用场景:
 *
 * 网页爬虫对URL的去重，避免爬取相同的URL地址
 *
 * 反垃圾邮件，从数十亿个垃圾邮件列表中判断某邮箱是否垃圾邮箱（同理，垃圾短信）
 *
 * 缓存穿透，将所有可能存在的数据缓存放到布隆过滤器中，当黑客访问不存在的缓存时迅速返回避免缓存及DB挂掉。
 * https://www.cnblogs.com/rinack/p/9712477.html
 * @Author majinhu
 * @Date 2020/2/13 21:17
 * @Version 1.0
 **/
public class BloomFilterTest {
    private static int size = 1000000;

    private static BloomFilter<Integer> bloomFilter =BloomFilter.create(Funnels.integerFunnel(), size);
    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        List<Integer> list = new ArrayList<Integer>(1000);
        //故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());
    }
}
