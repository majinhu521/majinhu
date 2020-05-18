package com.study.majinhu.designModel.Mvc;

/**
 * @ClassName StudentView
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:33
 * @Version 1.0
 **/
public class StudentView {
    public void printStudentDetails(String studentName, String studentRollNo){
        System.out.println("Student: ");
        System.out.println("Name: " + studentName);
        System.out.println("Roll No: " + studentRollNo);
    }
}