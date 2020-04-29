package com.study.majinhu.jdkBase.instanceOf;



/**
 * @ClassName InstanceofTest3
 * @Description
 * instanceof是Java中的二元运算符，左边是对象，右边是类；
 * 当对象是右边类或子类所创建对象时，返回true；否则，返回false。
 *
 * 这里说明下：
 *
 * 类的实例包含本身的实例，以及所有直接或间接子类的实例
 *
 * instanceof左边显式声明的类型与右边操作元必须是同种类或存在继承关系，也就是说需要位于同一个继承树，否则会编译错误
 * ————————————————
 * 版权声明：本文为CSDN博主「Cappuccinooo」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/kuangay/article/details/81563992
 * @Author majinhu
 * @Date 2019/12/25 11:03
 * @Version 1.0
 **/

public class InstanceofTest3 {

    public static void main(String[] args) {
        A a = null;
        B b = null;
        boolean result;
        result = a instanceof A;
        System.out.println(result); // 结果：false null用instanceof跟任何类型比较时都是false
        result = b instanceof B;
        System.out.println(result); // 结果：false null用instanceof跟任何类型比较时都是false


        a = new B();
        b = new B();
        result = a instanceof A;
        System.out.println(result); // 结果：true a是接口A的实例对象引用指向子类类B，类B实现了接口A，所以属于同一个继承树分支
        result = a instanceof B;
        System.out.println(result); // 结果：true a是接口A的实例对象引用指向子类类B，类B实现了接口A，所以属于同一个继承树分支
        result = b instanceof A;
        System.out.println(result);// 结果：true b是类B的实例对象，类B实现了接口A，所以属于同一个继承树分支
        result = b instanceof B;
        System.out.println(result);// 结果：true b是类B的实例对象，类B实现了接口A，所以属于同一个继承树分支


        B b2 = new C();
        result = b2 instanceof A;
        System.out.println(result); // 结果：true b2是父类B引用指向子类C，类B实现了接口A，所以属于同一个继承树分支
        result = b2 instanceof B;
        System.out.println(result); // 结果：true b2是父类B引用指向子类C，所以属于同一个继承树分支
        result = b2 instanceof C;
        System.out.println(result); // 结果：true b2是父类B引用指向子类C，所以属于同一个继承树分支
    }
}
