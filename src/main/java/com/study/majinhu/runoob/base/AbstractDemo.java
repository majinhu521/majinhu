package com.study.majinhu.runoob.base;

/**
 * @ClassName AbstractDemo
 * @Description
 * @Author majinhu
 * @Date 2021/2/22 14:31
 * @Version 1.0
 **/
public class AbstractDemo {
    public static void main(String [] args)
    {
        /* 以下是不允许的，会引发错误 */
        //EmployeeA e = new EmployeeA("George W.", "Houston, TX", 43);

//        System.out.println("\n Call mailCheck using Employee reference--");

        //e.mailCheck();

        Salary s = new Salary("Mohd Mohtashim", "Ambehta, UP", 3, 3600.00);
        EmployeeA e = new Salary("John Adams", "Boston, MA", 2, 2400.00);

        System.out.println("Call mailCheck using Salary reference --");
        s.mailCheck();

        System.out.println("\n Call mailCheck using Employee reference--");
        e.mailCheck();

        System.out.println(e.computePay());
    }
}
