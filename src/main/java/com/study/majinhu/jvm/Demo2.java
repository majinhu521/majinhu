package com.study.majinhu.jvm;

/**
 * @ClassName Demo2
 * @Description
 * @Author majinhu
 * @Date 2020/4/29 10:37
 * @Version 1.0
 **/
class YeYe{
    static {
        System.out.println("YeYe静态代码块");
    }
}

class Father extends YeYe{
    public static String strFather="HelloJVM_Father";

    static{
        System.out.println("Father静态代码块");
    }
}

class Son extends Father{
    public static String strSon="HelloJVM_Son";

    static{
        System.out.println("Son静态代码块");
    }
}

public class Demo2{
    public static void main(String[] args) {
        System.out.println(Son.strFather);
    }
}
