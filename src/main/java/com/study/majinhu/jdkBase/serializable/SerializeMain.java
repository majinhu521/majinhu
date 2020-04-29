package com.study.majinhu.jdkBase.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @ClassName SerializeMain
 * @Description
 * @Author majinhu
 * @Date 2019/12/20 9:09
 * @Version 1.0
 **/
public class SerializeMain {

    /**
     * @author Arpit Mandliya
     */
    public static void main(String[] args) {

        Employee emp = new Employee();
        emp.setEmployeeId(101);
        emp.setEmployeeName("Arpit");
        emp.setDepartment("CS");
        try
        {
            FileOutputStream fileOut = new FileOutputStream("employee.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(emp);
            outStream.close();
            fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }
}
