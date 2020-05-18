package com.study.majinhu.designModel.VisitorPattern;

/**
 * @ClassName Keyboard
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:29
 * @Version 1.0
 **/
public class Keyboard  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
