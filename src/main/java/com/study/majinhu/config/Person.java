package com.study.majinhu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Person
 * @Description
 * <dependency>
 * 	<groupId>org.springframework.boot</groupId>
 * 	<artifactId>spring-boot-configuration-processor</artifactId>
 * 	<optional>true</optional>
 * </dependency>
 * https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/html/configuration-metadata.html#configuration-metadata-annotation-processor
 * @Author majinhu
 * @Date 2019/12/25 9:29
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix="person")
public class Person {

    private String lastName;
    private Integer age;
    private Boolean child;
    private Date birth;
    private Map<String, Object> hobbies;
    private List<Object> friends;
    private Pet pet;
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Boolean getChild() {
        return child;
    }
    public void setChild(Boolean child) {
        this.child = child;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public Map<String, Object> getHobbies() {
        return hobbies;
    }
    public void setHobbies(Map<String, Object> hobbies) {
        this.hobbies = hobbies;
    }
    public List<Object> getFriends() {
        return friends;
    }
    public void setFriends(List<Object> friends) {
        this.friends = friends;
    }
    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    @Override
    public String toString() {
        return "Person [lastName=" + lastName + ", age=" + age + ", child=" + child + ", birth=" + birth + ", hobbies="
                + hobbies + ", friends=" + friends + ", pet=" + pet + "]";
    }

}

