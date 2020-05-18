package com.study.majinhu.designModel.VisitorPattern;

/**
 * @ClassName ComputerPartVisitor
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:31
 * @Version 1.0
 **/
public interface ComputerPartVisitor {
    public void visit(Computer computer);
    public void visit(Mouse mouse);
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}
