package com.study.majinhu.jdkBase.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @ClassName DeserializeMain
 * @Description
 * @Author majinhu
 * @Date 2019/12/20 9:10
 * @Version 1.0
 **/
public class DeserializeMain {
    /**
     * @author Arpit Mandliya
     */
    public static void main(String[] args) {

        Employee emp = null;
        try
        {
            FileInputStream fileIn =new FileInputStream("employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            emp = (Employee) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialized Employee...");
        System.out.println("Emp id: " + emp.getEmployeeId());
        System.out.println("Name: " + emp.getEmployeeName());
        System.out.println("Department: " + emp.getDepartment());
    }

    /**java.io.InvalidClassException: com.study.majinhu.jdkBase.serializable.Employee; local class incompatible:
     *  stream classdesc serialVersionUID = -7526423366109948302, local class serialVersionUID = 1
     */

}

