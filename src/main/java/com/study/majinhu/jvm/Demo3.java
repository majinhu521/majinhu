package com.study.majinhu.jvm;

/**
 * @ClassName Demo3
 * @Description https://my.oschina.net/u/4397303/blog/3247040/print
 * @Author majinhu
 * @Date 2020/4/29 10:39
 * @Version 1.0
 **/
class YeYe3{
    static {
        System.out.println("YeYe静态代码块");
    }
}

class Father3 extends YeYe3{
    public final static String strFather="HelloJVM_Father";

    static{
        System.out.println("Father静态代码块");
    }
}

class Son3 extends Father3{
    public static String strSon="HelloJVM_Son";

    static{
        System.out.println("Son静态代码块");
    }
}

public class Demo3 {
    public static void main(String[] args) {
        System.out.println(Son3.strFather);
    }
}
