package com.study.majinhu.designModel.Factory;

/**
 * @ClassName Rectangle
 * @Description 三角形
 * @Author majinhu
 * @Date 2019/6/12 16:38
 * @Version 1.0
 **/
public class Rectangle  implements  Shape{

    @Override
    public void draw() {
        System.out.println("三角形绘制method.");
    }
}
