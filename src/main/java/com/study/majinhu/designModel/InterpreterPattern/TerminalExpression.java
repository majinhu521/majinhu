package com.study.majinhu.designModel.InterpreterPattern;

/**
 * @ClassName TerminalExpression
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:13
 * @Version 1.0
 **/
public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}
