package com.study.majinhu.designModel.MementoPattern;

/**
 * @ClassName Memento
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:21
 * @Version 1.0
 **/
public class Memento {
    private String state;

    public Memento(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }
}
