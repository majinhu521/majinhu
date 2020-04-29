package com.study.majinhu.redis.bloomFilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Version;

import java.util.ArrayList;
import java.util.List;

/**
 垃圾邮件过滤。如果用哈希表，每存储一亿个 email地址，就需要 1.6GB的内存
 （用哈希表实现的具体办法是将每一个 email地址对应成一个八字节的信息指纹，
 然后将这些信息指纹存入哈希表，由于哈希表的存储效率一般只有 50%，
 因此一个 email地址需要占用十六个字节。一亿个地址大约要 1.6GB，即十六亿字节的内存）。
 因此存贮几十亿个邮件地址可能需要上百 GB的内存。
 而Bloom Filter只需要哈希表 1/8到 1/4 的大小就能解决同样的问题。
 原文链接：https://blog.csdn.net/u013805360/article/details/80785795
 * @Author majinhu
 * @Date 2020/2/13 21:17
 * @Version 1.0
 **/
public class BloomFilterTest2 {
    public static void main(String[] args) {
        long expectedInsertions = 10000000;
        double fpp = 0.00001;

        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), expectedInsertions, fpp);

        bloomFilter.put("aaa");
        bloomFilter.put("bbb");
        boolean containsString = bloomFilter.mightContain("aaa");
        System.out.println(containsString);

        BloomFilter<Email> emailBloomFilter = BloomFilter
                .create((Funnel<Email>) (from, into) -> into.putString(from.getDomain(), Charsets.UTF_8),
                        expectedInsertions, fpp);

        emailBloomFilter.put(new Email("sage.wang", "quanr.com"));
        boolean containsEmail = emailBloomFilter.mightContain(new Email("sage.wangaaa", "quanr.com"));
        System.out.println(containsEmail);
    }

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    public static class Email {
        private String userName;
        private String domain;
    }


}
