package com.study.majinhu.designModel.FilterPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName CriteriaPatternDemo
 * @Description 过滤器模式
 * 过滤器模式（Filter Pattern）或标准模式（Criteria Pattern）是一种设计模式，
 * 这种模式允许开发人员使用不同的标准来过滤一组对象，
 * 通过逻辑运算以解耦的方式把它们连接起来。这种类型的设计模式属于结构型模式，它结合多个标准来获得单一标准。
 * @Author majinhu
 * @Date 2020/2/25 16:48
 * @Version 1.0
 **/
public class CriteriaPatternDemo {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Robert","Male", "Single"));
        persons.add(new Person("John","Male", "Married"));
        persons.add(new Person("Laura","Female", "Married"));
        persons.add(new Person("Diana","Female", "Single"));
        persons.add(new Person("Mike","Male", "Single"));
        persons.add(new Person("Bobby","Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("Females: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("Single Males: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("Single Or Females: ");
        printPersons(singleOrFemale.meetCriteria(persons));

        //lambda表达式 list and 过滤
        List<Person> men = persons.stream()
                .filter(o -> "Male".equalsIgnoreCase(o.getGender()) )
                .filter(o -> "Married".equalsIgnoreCase(o.getMaritalStatus()))
                .collect(Collectors.toList());
        System.out.println("Male and Married:" + men);
        printPersons(men);


        //https://www.cnblogs.com/tekikesyo/p/12712002.html

        //lambda表达式 list or 合并
        List<Person> men2 = persons.stream()
                .filter(o -> "Female".equalsIgnoreCase(o.getGender()) )
                .collect(Collectors.toList());
        List<Person> newlist = new ArrayList<>(men2);
        List<Person> men3 = persons.stream()
                .filter(o -> "Single".equalsIgnoreCase(o.getMaritalStatus()))
                .collect(Collectors.toList());
        newlist.addAll(men3);
        System.out.println("Single Or Females2: ");
        printPersons(newlist);

        //lambda表达式 list or 合并
        List<Person> list1 = persons.stream()
                .filter(o -> "Female".equalsIgnoreCase(o.getGender()) )
                .collect(Collectors.toList());
        List<Person> list2 = persons.stream()
                .filter(o -> "Single".equalsIgnoreCase(o.getMaritalStatus()))
                .collect(Collectors.toList());
        List<Person> newlist1 = new ArrayList<>();
        newlist1 = Stream.concat(list1.stream(),list2.stream()).collect(Collectors.toList());
        System.out.println("Single Or Females3: ");
        printPersons(newlist1);

    }

    public static void printPersons(List<Person> persons){
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    +", Gender : " + person.getGender()
                    +", Marital Status : " + person.getMaritalStatus()
                    +" ]");
        }
    }

}
