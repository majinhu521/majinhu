package com.study.majinhu.designModel.StrategyServer.demo2;

/**
 * @ClassName Context
 * @Description Context 是一个使用了某种策略的类。
 * @Author majinhu
 * @Date 2019/7/25 15:41
 * @Version 1.0
 **/
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
