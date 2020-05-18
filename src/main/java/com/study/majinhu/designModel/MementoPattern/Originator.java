package com.study.majinhu.designModel.MementoPattern;

/**
 * @ClassName Originator
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:22
 * @Version 1.0
 **/
public class Originator {
    private String state;

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public Memento saveStateToMemento(){
        return new Memento(state);
    }

    public void getStateFromMemento(Memento Memento){
        state = Memento.getState();
    }
}
