package com.study.majinhu.designModel.StatePattern;

/**
 * @ClassName StatePatternDemo
 * @Description 状态模式
 * https://www.runoob.com/design-pattern/state-pattern.html
 * @Author majinhu
 * @Date 2020/5/18 10:26
 * @Version 1.0
 **/
public class StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}