package com.study.majinhu.designModel.singleton;

/**
 * @ClassName SingleObject
 * @Description 单例模式
 * https://www.runoob.com/design-pattern/singleton-pattern.html
 * @Author majinhu
 * @Date 2019/7/25 15:51
 * @Version 1.0
 **/
public class SingleObject {
    //创建 SingleObject 的一个对象
    private static SingleObject instance = new SingleObject();

    //让构造函数为 private，这样该类就不会被实例化
    private SingleObject(){}

    //获取唯一可用的对象
    public static SingleObject getInstance(){
        return new SingleObject();
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}
