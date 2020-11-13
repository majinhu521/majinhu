package com.study.majinhu.designModel.NullObjectPattern;

/**
 * @ClassName NullPatternDemo
 * @Description 空对象模式
 * https://www.runoob.com/design-pattern/null-object-pattern.html
 * @Author majinhu
 * @Date 2020/5/18 10:28
 * @Version 1.0
 **/
public class NullPatternDemo {
    public static void main(String[] args) {

        AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
        AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");
        AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
        AbstractCustomer customer4 = CustomerFactory.getCustomer("Laura");

        System.out.println("Customers");
        System.out.println(customer1.getName());
        System.out.println(customer2.getName());
        System.out.println(customer3.getName());
        System.out.println(customer4.getName());
    }
}