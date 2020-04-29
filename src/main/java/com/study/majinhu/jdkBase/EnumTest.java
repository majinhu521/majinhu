package com.study.majinhu.jdkBase;
/**
 * @Author majinhu
 * @Description 枚举类型
 * @Date 17:02 2019/7/9
 * @Param
 * @return
 **/
public enum EnumTest {
    MON, TUE, WED, THU, FRI, SAT, SUN;

    public static void main(String[] args) {
        for (EnumTest e : EnumTest.values()) {
            System.out.println(e.toString());
        }

        System.out.println("----------------我是分隔线------------------");

        EnumTest test = EnumTest.TUE;
        switch (test) {
            case MON:
                System.out.println("今天是星期一");
                break;
            case TUE:
                System.out.println("今天是星期二");
                break;
            // ... ...
            default:
                System.out.println(test);
                break;
        }
    }



}