package com.study.majinhu.jdkBase;

import java.lang.reflect.Method;

/**
 * @ClassName MethodInvoke
 * @Description java反射之Method的invoke方法实现
 * @Author majinhu
 * @Date 2019/6/18 14:39
 * @Version 1.0
 **/
public class MethodInvoke {

    public static void main(String[] args) throws Exception {
        Method animalMethod = Animal.class.getDeclaredMethod("print");
        Method catMethod = Cat.class.getDeclaredMethod("print");

        Animal animal = new Animal();
        Cat cat = new Cat();
        animalMethod.invoke(cat);
        animalMethod.invoke(animal);

        catMethod.invoke(cat);
        catMethod.invoke(animal);
    }

}

class Animal {

    public void print() {
        System.out.println("Animal.print()");
    }

}

class Cat extends Animal {

    @Override
    public void print() {
        System.out.println("Cat.print()");
    }


}
