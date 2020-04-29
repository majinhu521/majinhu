package com.study.majinhu.jdkBase.constructhanshu;

/**
 * @ClassName Person
 * @Description 构造函数
 * https://www.cnblogs.com/huxins/p/9017212.html
 * @Author majinhu
 * @Date 2019/12/25 9:29
 * @Version 1.0
 **/
public class Person {

    String name; //    姓名
    int age; //    年龄

    public static void main(String[] args) {
        Person p = new Person(); //    创建了Person类型的p对象
        System.out.println("姓名: " + p.name + " 年龄: " + p.age); //    name = null, age = 0;
        //这个小孩刚出生的时候没有姓名和年龄
    }
}

//构造函数初始赋值
//构造方法作用：
//        1).创建对象,凡是必须和new一起使用.
//        2).对对象进行初始化.
class Person2 {

    String name; //    姓名
    int age; //    年龄

    //    构造方法
    Person2(String name, int age) {
        this.name = name; //     给对象赋予name值
        this.age = age; //    给对象赋予age值
    }

    public static void main(String[] args) {
        Person2 p = new Person2("张三", 1); //    创建了Person类型的p对象,并且调用构造方法赋予该对象属性值
        System.out.println("姓名: " + p.name + " 年龄: " + p.age); //    name = 张三, age = 1;
        //这个小孩刚出生的时候已经有了姓名和年龄
    }
}

//构造代码块作用：给所有的对象进行统一的初始化
//1：构造代码块和构造函数的区别，构造代码块是给所有对象进行统一初始化， 构造函数给对应的对象初始化。
//2：构造代码块的作用：它的作用就是将所有构造方法中公共的信息进行抽取。
class Boy {

    String name;
    int age;
    String gender;
    // 构造代码块,给所有对象进行初始化。
    {
        System.out.println("哭。。。");
    }

    Boy() {
        System.out.println("无参构造");
    }

    Boy(String n, int a, String g) {
        name = n;
        age = a;
        gender = g;
        System.out.println("有参构造"+n +a +g);
    }

    void run() {
        System.out.println("跑...");
    }

}

class Demo9 {

    public static void main(String[] args) {

        System.out.println();
        Boy b = new Boy();

        Boy b2 = new Boy("jack", 1, "男");

    }
}

