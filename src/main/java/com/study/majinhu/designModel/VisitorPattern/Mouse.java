package com.study.majinhu.designModel.VisitorPattern;

/**
 * @ClassName Mouse
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:30
 * @Version 1.0
 **/
public class Mouse  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}