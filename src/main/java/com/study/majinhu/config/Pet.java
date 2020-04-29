package com.study.majinhu.config;

/**
 * @ClassName Pet
 * @Description
 * @Author majinhu
 * @Date 2020/2/19 10:19
 * @Version 1.0
 **/
public class Pet {
    private String name;
    private Integer age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Pet [name=" + name + ", age=" + age + "]";
    }

}
