package com.study.majinhu.jdkBase;

/**
 * @ClassName BRRead
 * @Description
 * 从 BufferedReader 对象读取一个字符要使用 read() 方法，它的语法如下：
 * @Author majinhu
 * @Date 2019/7/1 10:57
 * @Version 1.0
 **/
//使用 BufferedReader 在控制台读取字符

import java.io.*;

public class BRRead {
    public static void main(String args[]) throws IOException {
        char c;
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'q' 键退出。");
        // 读取字符
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != 'q');
    }
}