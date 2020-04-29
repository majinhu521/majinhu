package com.study.majinhu.Concurrent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName ThreadLocalRandomTest
 * @Description
 * Random 的缺点是多个线程会使用原子性种子变量，会导致对原子变量更新的竞争
 * ThreadLocalRandom 原理
 * 如果每个线程维护自己的一个种子变量，每个线程生成随机数时候根据自己老的种子计算新的种子，
 * 并使用新种子更新老的种子，然后根据新种子计算随机数，就不会存在竞争问题，这会大大提高并发性能，
 *
 * @Author majinhu
 * @Date 2019/6/19 11:17
 * @Version 1.0
 **/
public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        //(10)获取一个随机数生成器
        ThreadLocalRandom random =  ThreadLocalRandom.current();
        //(11)输出10个在0-5（包含0，不包含5）之间的随机数
        for (int i = 0; i < 10; ++i) {
           System.out.println(random.nextInt(5));
        }

        //(1)创建一个默认种子的随机数生成器
        Random random1 = new Random();        //(2)输出10个在0-5（包含0，不包含5）之间的随机数
        for (int i = 0; i < 10; ++i) {
            //System.out.println(random1.nextInt(5));
        }
    }

}
