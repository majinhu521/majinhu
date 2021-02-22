package com.study.majinhu.jdkBase.JDK18new;

/**
 * @ClassName StreamSetDemo
 * @Description
 * @Author majinhu
 * @Date 2021/2/22 9:49
 * @Version 1.0
 **/

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class StreamSetDemo {
    public static void main(String[] args) {
        Set<Student> set = new HashSet<>();
        set.add(new Student(1, "Mahesh", 12));
        set.add(new Student(2, "Suresh", 15));
        set.add(new Student(3, "Nilesh", 10));

        System.out.println("---Natural Sorting by Name---");
        System.out.println("---Natural Sorting by Name---");
        set.stream().sorted().forEach(e -> System.out.println("Id:"
                + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));

        System.out.println("---Natural Sorting by Name in reverse order---");
        set.stream().sorted(Comparator.reverseOrder()).forEach(e -> System.out.println("Id:"
                + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));

        System.out.println("---Sorting using Comparator by Age---");
        set.stream().sorted(Comparator.comparing(Student::getAge))
                .forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));

        System.out.println("---Sorting using Comparator by Age in reverse order---");
        set.stream().sorted(Comparator.comparing(Student::getAge).reversed())
                .forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));
    }
}
