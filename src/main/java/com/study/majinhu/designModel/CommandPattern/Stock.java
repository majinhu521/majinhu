package com.study.majinhu.designModel.CommandPattern;

/**
 * @ClassName Stock
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:09
 * @Version 1.0
 **/
public class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy(){
        System.out.println("Stock [ Name: "+name+",Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+",Quantity: " + quantity +" ] sold");
    }
}