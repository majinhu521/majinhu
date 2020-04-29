package com.study.majinhu.designModel.Bridge;

/**
 * @ClassName Shape
 * @Description
 * @Author majinhu
 * @Date 2020/2/25 16:34
 * @Version 1.0
 **/
public abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}