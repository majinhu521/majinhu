package com.study.majinhu.designModel.StatePattern;



/**
 * @ClassName StartState
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:24
 * @Version 1.0
 **/
public class StartState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString(){
        return "Start State";
    }
}