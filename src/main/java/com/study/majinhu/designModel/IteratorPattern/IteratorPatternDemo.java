package com.study.majinhu.designModel.IteratorPattern;

/**
 * @ClassName IteratorPatternDemo
 * @Description 迭代器模式
 * https://www.runoob.com/design-pattern/iterator-pattern.html
 * @Author majinhu
 * @Date 2020/5/18 10:17
 * @Version 1.0
 **/
public class IteratorPatternDemo {
    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.println("Name : " + name);
        }
    }
}
