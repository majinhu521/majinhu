package com.study.majinhu.designModel.VisitorPattern;

/**
 * @ClassName VisitorPatternDemo
 * @Description 访问者模式
 * https://www.runoob.com/design-pattern/visitor-pattern.html
 * @Author majinhu
 * @Date 2020/5/18 10:31
 * @Version 1.0
 **/
public class VisitorPatternDemo {
    public static void main(String[] args) {

        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
