package com.study.majinhu.runoob.base;

/**
 * @ClassName TestDog
 * @Description 重写
 * @Author majinhu
 * @Date 2021/2/22 14:03
 * @Version 1.0
 **/
class Animal{
    public void move(){
        System.out.println("动物可以移动");
    }
}

class Dog extends Animal{
    public void move(){
        System.out.println("狗可以跑和走");
    }
}

public class TestDog{
    public static void main(String args[]){
        Animal a = new Animal(); // Animal 对象
        Animal b = new Dog(); // Dog 对象

        a.move();// 执行 Animal 类的方法
        b.move();//执行 Dog 类的方法

    }
}