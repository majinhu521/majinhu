package com.study.majinhu.designModel.AbstractFactory;

/**
 * @ClassName ColorFactory
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:29
 * @Version 1.0
 **/
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}