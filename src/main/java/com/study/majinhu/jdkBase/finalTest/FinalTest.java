package com.study.majinhu.jdkBase.finalTest;

/**
 * @ClassName FinalTest
 * @Description
 * https://www.cnblogs.com/zxfei/p/10735225.html
 * 1. final类
 * final类不能被继承，因此final类的成员方法没有机会被覆盖，默认都是final的。在设计类时候，如果这个类不需要有子类，类的实现细节不允许改变，并且确信这个类不会载被扩展，那么就设计为final类。
 * 2.final方法
 * 如果一个类不允许其子类覆盖某个方法（即不允许被子类重写），则可以把这个方法声明为final方法。
 * 使用final方法的原因有二：
 * 把方法锁定，防止任何继承类修改它的意义和实现。
 * 高效。编译器在遇到调用final方法时候会转入内嵌机制，大大提高执行效率。
 * 3. final变量(常量)
 * 用final修饰的成员变量表示常量，值一旦给定就无法改变！
 * final修饰的变量有三种：静态变量、实例变量和局部变量，分别表示三种类型的常量。
 * 一旦给final变量初值后，值就不能再改变了。
 * 另外，final变量定义的时候，可以先声明，而不给初值，这中变量也称为final空白，无论什么情况，编译器都确保空白final在使用之前必须被初始化。
 * 但是，final空白在final关键字final的使用上提供了更大的灵活性，
 * 为此，一个类中的final数据成员就可以实现依对象而有所不同，却有保持其恒定不变的特征。
 * @Author majinhu
 * @Date 2019/12/26 16:08
 * @Version 1.0
 **/
public  class FinalTest {
    private  int inta= 123;
    private static int intb= 123;
    private static final int intc= 123;

    public  int intpub= 123;
    public  final  int intfinal= 123;
    public static int intd= 123;
    public static final int intf = 123;

    public  final void method(){
       System.out.println("this is final method");
    }
    private   final void final_priv_method(){
        System.out.println("this is final method");
    }
    public   void method2(){
        System.out.println("this is normal method");
    }
    private   void prv_method2(){
        System.out.println("this is normal  private method");
    }

    public static void main(String[] args) {
        FinalTest a = new FinalTest();
        a.inta=4;
        System.out.println(a.inta);
        FinalTest.intb=7;//静态变量可以被赋值
        System.out.println(FinalTest.intb);
//        FinalTest.intc=7;//final变量不可以被赋值，不可以修改
        System.out.println(FinalTest.intc);

    }

}
