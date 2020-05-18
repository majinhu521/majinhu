package com.study.majinhu.designModel.StatePattern;

/**
 * @ClassName StopState
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:25
 * @Version 1.0
 **/
public class StopState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString(){
        return "Stop State";
    }
}