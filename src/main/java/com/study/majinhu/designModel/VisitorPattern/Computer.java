package com.study.majinhu.designModel.VisitorPattern;

/**
 * @ClassName Computer
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:30
 * @Version 1.0
 **/
public class Computer implements ComputerPart {

    ComputerPart[] parts;

    public Computer(){
        parts = new ComputerPart[] {new Mouse(), new Keyboard(), new Monitor()};
    }


    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        for (int i = 0; i < parts.length; i++) {
            parts[i].accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);
    }
}