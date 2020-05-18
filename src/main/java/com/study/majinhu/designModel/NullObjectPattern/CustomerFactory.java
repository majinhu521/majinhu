package com.study.majinhu.designModel.NullObjectPattern;

/**
 * @ClassName CustomerFactory
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:28
 * @Version 1.0
 **/
public class CustomerFactory {

    public static final String[] names = {"Rob", "Joe", "Julie"};

    public static AbstractCustomer getCustomer(String name){
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(name)){
                return new RealCustomer(name);
            }
        }
        return new NullCustomer();
    }
}