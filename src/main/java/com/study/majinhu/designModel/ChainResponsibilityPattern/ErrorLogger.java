package com.study.majinhu.designModel.ChainResponsibilityPattern;

/**
 * @ClassName ErrorLogger
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:05
 * @Version 1.0
 **/
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}