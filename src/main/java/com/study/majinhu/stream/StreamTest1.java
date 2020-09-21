package com.study.majinhu.stream;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * @ClassName StreamTest1
 * @Description stream 去重复元素。
 * https://blog.csdn.net/weixin_34185560/article/details/91464917
 * https://blog.csdn.net/y_k_y/article/details/84633001
 * @Author majinhu
 * @Date 2020/9/8 15:11
 * @Version 1.0
 **/
public class StreamTest1 {
    public static void main(String[] args) {
//        String positionStr = "1,2,3,4,4";
//        List<String> list = Arrays.asList(positionStr.split(","));
//        List<String> list2 = list.stream()
//                .filter(obj -> !obj.isEmpty()).distinct().collect(Collectors.toList());

        // 1. 对于 String 列表去重
        List<String> stringList = new ArrayList<String>() {{
            add("A");
            add("A");
            add("B");
            add("B");
            add("C");
        }};
        out.print("去重前：");
        for (String s : stringList) {
            out.print(s);
        }
        out.println();
        stringList = stringList.stream().distinct().collect(Collectors.toList());
        out.print("去重后：");
        for (String s : stringList) {
            out.print(s);
        }
        out.println();
        out.println("UUID.randomUUID()" + UUID.randomUUID());

        try {
            //规定格式 (格式根据自己数据库取得的数据进行规范)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取当前时间
            String dateOne = sdf.format(new Date());
            Date dateTwo = new Date();
            Date dateThree = sdf.parse("2019-05-20 12:00:00");
            //时间date类型 和 时间String类型
            if (dateThree.getTime() >= sdf.parse(dateOne).getTime()) {
                //逻辑代码 .............
                System.out.println("1进入");
            }
            //时间date类型 和 时间date类型
            if (dateTwo.getTime() >= dateThree.getTime()) {
                //逻辑代码 .............
                System.out.println("2进入");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}


