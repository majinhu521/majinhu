package com.study.majinhu.designModel.Bridge;

/**
 * @ClassName Circle
 * @Description 创建实现了 Shape 接口的实体类。
 * @Author majinhu
 * @Date 2020/2/25 16:35
 * @Version 1.0
 **/
public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}