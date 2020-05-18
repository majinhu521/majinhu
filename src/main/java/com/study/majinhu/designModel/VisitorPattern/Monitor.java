package com.study.majinhu.designModel.VisitorPattern;

/**
 * @ClassName Monitor
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:30
 * @Version 1.0
 **/
public class Monitor  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}