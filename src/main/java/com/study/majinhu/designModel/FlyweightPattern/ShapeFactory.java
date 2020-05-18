package com.study.majinhu.designModel.FlyweightPattern;

import java.util.HashMap;

/**
 * @ClassName ShapeFactory
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 9:56
 * @Version 1.0
 **/
public class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color);

        if(circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}