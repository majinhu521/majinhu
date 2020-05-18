package com.study.majinhu.designModel.MementoPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CareTaker
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:22
 * @Version 1.0
 **/
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
}
