package com.study.majinhu.designModel.StrategyServer.demo2;

/**
 * @ClassName StrategyPatternDemo
 * @Description 我们的演示类使用 Context 和策略对象来演示 Context 在它所配置或使用的策略改变时的行为变化。
 * 策略模式主要是用来封装一组可以互相替代的算法族，并且可以根据需要动态地去替换 Context 使用的算法。
 * @Author majinhu
 * @Date 2019/7/25 15:43
 * @Version 1.0
 **/
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}