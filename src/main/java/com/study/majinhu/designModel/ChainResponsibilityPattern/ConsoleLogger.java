package com.study.majinhu.designModel.ChainResponsibilityPattern;

/**
 * @ClassName ConsoleLogger
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:05
 * @Version 1.0
 **/
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
