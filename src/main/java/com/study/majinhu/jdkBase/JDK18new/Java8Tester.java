package com.study.majinhu.jdkBase.JDK18new;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Java8Tester
 * @Description
 * @Author majinhu
 * @Date 2019/12/25 11:22
 * @Version 1.0
 **/
public class Java8Tester {
    public static void main(String args[]){
        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }
}