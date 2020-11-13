package com.study.majinhu.designModel.InterpreterPattern;

/**
 * @ClassName Expression
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:13
 * @Version 1.0
 **/
public interface Expression {
    public boolean interpret(String context);
}