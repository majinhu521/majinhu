package com.study.majinhu.designModel.VisitorPattern;

/**
 * @ClassName ComputerPart
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:29
 * @Version 1.0
 **/
public interface ComputerPart {
        public void accept(ComputerPartVisitor computerPartVisitor);
}

