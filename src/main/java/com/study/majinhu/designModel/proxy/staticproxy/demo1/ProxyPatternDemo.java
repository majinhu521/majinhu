package com.study.majinhu.designModel.proxy.staticproxy.demo1;

/**
 * @ClassName ProxyPatternDemo
 * @Description
 * @Author majinhu
 * @Date 2019/6/21 9:27
 * @Version 1.0
 **/
public class ProxyPatternDemo {
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }
}
