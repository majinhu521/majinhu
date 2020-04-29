package com.study.majinhu.jdkBase.fanxing;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestT
 * @Description https://www.cnblogs.com/dirgo/p/9323595.html
 * 下来说说泛型通配符T，E，K，V区别
 * 使用大写字母A,B,C,D......X,Y,Z定义的，就都是泛型，把T换成A也一样，这里T只是名字上的意义而已
 *
 * ？ 表示不确定的java类型
 * T (type) 表示具体的一个java类型
 * K V (key value) 分别代表java键值中的Key Value
 * E (element) 代表Element
 * @Author majinhu
 * @Date 2019/12/25 15:19
 * @Version 1.0
 **/
public class TestT<T> {
    public List<T> list = new ArrayList<T>();
    public static void main(String[] args) {
        TestT<String> test = new TestT<String>();
        test.list.add("hello");
        System.out.println(test.list);
    }}