package com.study.majinhu.designModel.ChainResponsibilityPattern.demo1;

import lombok.Data;

/**
 * @ClassName Handler
 * @Description // 处理请假 三个具体处理类，分别实现了抽象处理类的 process 方法
 * @Author majinhu
 * @Date 2021/9/2 10:44
 * @Version 1.0
 **/
@Data
public abstract class Handler {
    protected String name; // 处理者姓名
    protected Handler nextHandler; // 下一个处理者

    public Handler(String name) {
        this.name = name;
    }

    public abstract boolean process(LeaveRequest leaveRequest);
}

