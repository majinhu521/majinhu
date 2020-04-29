package com.study.majinhu.jdkBase.exception;

/**
 * @ClassName ExcepTest
 * @Description
 * @Author majinhu
 * @Date 2019/7/5 10:49
 * @Version 1.0
 **/
public class ExcepTest{

//    public static void main(String args[]){
//        try{
//            int a[] = new int[2];
//            System.out.println("Access element three :" + a[3]);
//        }catch(ArrayIndexOutOfBoundsException e){
//            System.out.println("Exception thrown  :" + e);
//        }
//        System.out.println("Out of the block");
//    }

    public static void main(String args[]){
        int a[] = new int[2];
        try{
            System.out.println("Access element three :" + a[3]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Exception thrown  :" + e);
        }
        finally{
            a[0] = 6;
            System.out.println("First element value: " +a[0]);
            System.out.println("The finally statement is executed");
        }
    }
}