package com.study.majinhu.jdkBase.finalTest;

/**
 * @ClassName FinalTest2
 * @Description
 * 1.extends FinalTest ，final类不能被继承
 * 2
 * final修饰方法表示此方法已经是"最后的、最终的”含义，即此方法不能被重写，但是可以被重载。
 * PS：重写的前提是子类可以继承父类的方法，如果父类中final修饰的方法同时设置权限为private，
 * 则会导致子类不能继承此方法。但是，可以在子类中重新定义相同方法名和参数的方法，此时不会产生重写与final的矛盾。
 * 而是在子类中重新定义了新的方法。
 * 因为父类中及被private又被final修饰的那个方法根本没有被继承，子类在重新定义这个同名同参数方法时不会发生编译错误。
 * @Author majinhu
 * @Date 2019/12/26 16:09
 * @Version 1.0
 **/
public class FinalTest2 extends FinalTest {

//    private    void final_priv_method(){
//        System.out.println("this is final method");
//    }
    public static void main(String[] args) {
        FinalTest2 finalTest2 = new FinalTest2();
        finalTest2.method();
        finalTest2.method2();
        System.out.println(finalTest2.intfinal);//子类可访问父类的 public  final 或者 public变量。不能访问static变量，
        System.out.println(finalTest2.intpub);
    }

}
