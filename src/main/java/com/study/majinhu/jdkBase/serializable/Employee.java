package com.study.majinhu.jdkBase.serializable;

import java.io.Serializable;

/**
 * @ClassName Employee
 * @Description
 * https://www.oschina.net/translate/serialization-in-java
 * @Author majinhu
 *
 * 把对象转换为字节序列的过程称为对象的序列化
 * 把字节序列恢复为对象的过程称为对象的反序列化
 *
 * 在序列化的时候系统将serialVersionUID写入到序列化的文件中去，
 * 当反序列化的时候系统会先去检测文件中的serialVersionUID是否跟当前的文件的serialVersionUID是否一致，
 * 如果一致则反序列化成功，否则就说明当前类跟序列化后的类发生了变化，
 * 比如是成员变量的数量或者是类型发生了变化，那么在反序列化时就会发生crash，并且回报出错误：
 * @Date 2019/12/20 9:08
 * @Version 1.0
 **/
public class Employee implements Serializable {

    private static final long serialVersionUID = -7526423366109948302L;

    int employeeId;
    String employeeName;
    String department;

    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
}