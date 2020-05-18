package com.study.majinhu.designModel.StatePattern;

/**
 * @ClassName Context
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:25
 * @Version 1.0
 **/
public class Context {
    private State state;

    public Context() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}