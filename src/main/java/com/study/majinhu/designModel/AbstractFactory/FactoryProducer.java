package com.study.majinhu.designModel.AbstractFactory;

/**
 * @ClassName FactoryProducer
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:30
 * @Version 1.0
 **/
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}