package com.study.majinhu.runoob.base;

/**
 * @ClassName TestDog2
 * @Description
 * @Author majinhu
 * @Date 2021/2/22 14:10
 * @Version 1.0
 **/
class Animal1{
    public void move(){
        System.out.println("动物可以移动");
    }
}

class Dog1 extends Animal1{
    public void move(){
        super.move(); // 应用super类的方法
        System.out.println("狗可以跑和走");
    }
}

public class TestDog2{
    public static void main(String args[]){

        Animal1 b = new Dog1(); // Dog 对象
        b.move(); //执行 Dog类的方法

    }
}
