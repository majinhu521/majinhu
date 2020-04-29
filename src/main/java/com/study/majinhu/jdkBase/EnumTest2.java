package com.study.majinhu.jdkBase;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

public class EnumTest2 {
    public static void main(String[] args) {
        EnumTest test = EnumTest.TUE;

        //compareTo(E o)
        switch (test.compareTo(EnumTest.MON)) {
            case -1:
                System.out.println("TUE 在 MON 之前");
                break;
            case 1:
                System.out.println("TUE 在 MON 之后");
                break;
            default:
                System.out.println("TUE 与 MON 在同一位置");
                break;
        }

        //getDeclaringClass()
        System.out.println("getDeclaringClass(): " + test.getDeclaringClass().getName());

        //name() 和  toString()
        System.out.println("name(): " + test.name());
        System.out.println("toString(): " + test.toString());

        //ordinal()， 返回值是从 0 开始
        System.out.println("ordinal(): " + test.ordinal());


            // EnumSet的使用
            EnumSet<EnumTest> weekSet = EnumSet.allOf(EnumTest.class);
            for (EnumTest day : weekSet) {
                System.out.println(day);
            }

            // EnumMap的使用
            EnumMap<EnumTest, String> weekMap = new EnumMap(EnumTest.class);
            weekMap.put(EnumTest.MON, "星期一");
            weekMap.put(EnumTest.TUE, "星期二");
            // ... ...
            for (Iterator<Map.Entry<EnumTest, String>> iter = weekMap.entrySet().iterator(); iter.hasNext();) {
                Map.Entry<EnumTest, String> entry = iter.next();
                System.out.println(entry.getKey().name() + ":" + entry.getValue());
            }

    }
}
