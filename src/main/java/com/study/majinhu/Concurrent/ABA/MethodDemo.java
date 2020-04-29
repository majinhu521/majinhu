package com.study.majinhu.Concurrent.ABA;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MethodDemo
 * @Description
 * @Author majinhu
 * @Date 2019/6/27 13:47
 * @Version 1.0
 **/
public class MethodDemo {
    private static AtomicInteger atomic = new AtomicInteger(2);

    public static void main(String[] args) {
        atomic.getAndAccumulate(10, (s1, s2) -> (s1 + s2) * s2);
        System.out.println(atomic.get());//计算结果：120
    }

}
