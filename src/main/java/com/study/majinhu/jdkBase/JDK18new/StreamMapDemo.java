package com.study.majinhu.jdkBase.JDK18new;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName StreamMapDemo
 * @Description
 * @Author majinhu
 * @Date 2021/2/22 9:50
 * @Version 1.0
 **/
public class StreamMapDemo {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(15, "Mahesh");
        map.put(10, "Suresh");
        map.put(30, "Nilesh");

        System.out.println("---Sort by Map Value---");
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(e -> System.out.println("Key: " + e.getKey() + ", Value: " + e.getValue()));

        System.out.println("---Sort by Map Key---");
        System.out.println("---Sort by Map Key---");
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(e -> System.out.println("Key: " + e.getKey() + ", Value: " + e.getValue()));
    }
}
