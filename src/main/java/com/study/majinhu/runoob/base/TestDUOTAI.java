package com.study.majinhu.runoob.base;


/**
 * @ClassName TestDUOTAI
 * @Description 多态：继承，重写，父类指向子路对象
 * @Author majinhu
 * @Date 2021/2/22 14:21
 * @Version 1.0
 **/
public class TestDUOTAI {
    public static void main(String[] args) {
        show(new Cat2());  // 以 Cat 对象调用 show 方法
        show(new Dog2());  // 以 Dog 对象调用 show 方法

        Animal2 a = new Cat2();  // 向上转型
        a.eat();               // 调用的是 Cat 的 eat
        Cat2 c = (Cat2)a;        // 向下转型
        c.work();        // 调用的是 Cat 的 work
    }

    public static void show(Animal2 a)  {
        a.eat();
        // 类型判断
        if (a instanceof Cat2)  {  // 猫做的事情
            Cat2 c = (Cat2)a;
            c.work();
        } else if (a instanceof Dog2) { // 狗做的事情
            Dog2 c = (Dog2)a;
            c.work();
        }
    }
}

abstract class Animal2 {
    abstract void eat();
}

class Cat2 extends Animal2 {
    public void eat() {
        System.out.println("吃鱼");
    }
    public void work() {
        System.out.println("抓老鼠");
    }
}

class Dog2 extends Animal2 {
    public void eat() {
        System.out.println("吃骨头");
    }
    public void work() {
        System.out.println("看家");
    }
}
