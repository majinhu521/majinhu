package com.study.majinhu.designModel.StrategyServer.demo2;

/**
 * @ClassName OperationSubstract
 * @Description
 * @Author majinhu
 * @Date 2019/7/25 15:40
 * @Version 1.0
 **/
public class OperationSubstract implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}