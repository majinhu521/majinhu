package com.study.majinhu.jdkBase.constructhanshu;

/**
 * @ClassName StaticCode
 * @Description https://www.cnblogs.com/bigben223/p/7986179.html
 * static
 * 55
 * 20,
 * show:20,
 * 55
 * 20,
 * 总结: static 代码块随着类的加载，只加载一次。作用是初始化类。
 * // 构造代码块，可以给所有对象进行初始化的。创建几个对象就调用几次，是给对象进行通用性的初始化。
 * // 构造函数是给对应的对象进行针对性的初始化。
 * //	局部代码块限定函数中变量的生命周期,age=30局部代码执行完。自动释放掉了。
 * @Author majinhu
 * @Date 2019/12/25 9:37
 * @Version 1.0
 **/
class StaticCode{
    int age;
    // static 代码块
    static{
        System.out.println("static ");
    }
    //构造代码块
    {
        System.out.println("55 ");
    }
    // 构造函数
    StaticCode(int age){
        this.age=age;
        System.out.println(age+",");
    }
    void show(){
        // 局部代码块
        {
            int age=30;
        }
        System.out.println("show:"+age+",");
    }
}
class StaticCodeDemo{
    public static void main(String[] args){
        StaticCode p1=new StaticCode(20);
        p1.show();
        StaticCode p2=new StaticCode(20);
    }
}
