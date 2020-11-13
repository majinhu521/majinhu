package com.study.majinhu.designModel.ChainResponsibilityPattern;

/**
 * @ClassName FileLogger
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:06
 * @Version 1.0
 **/
public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}