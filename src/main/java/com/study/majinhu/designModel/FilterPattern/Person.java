package com.study.majinhu.designModel.FilterPattern;

/**
 * @ClassName Person
 * @Description
 * @Author majinhu
 * @Date 2020/2/25 16:45
 * @Version 1.0
 **/
public class Person {
    private String name;
    private String gender;
    private String maritalStatus;

    public Person(String name,String gender,String maritalStatus){
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
}
