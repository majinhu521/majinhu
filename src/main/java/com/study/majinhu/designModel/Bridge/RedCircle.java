package com.study.majinhu.designModel.Bridge;

/**
 * @ClassName RedCircle
 * @Description
 * @Author majinhu
 * @Date 2020/2/25 16:34
 * @Version 1.0
 **/
public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}