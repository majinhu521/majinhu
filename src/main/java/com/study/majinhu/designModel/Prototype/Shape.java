package com.study.majinhu.designModel.Prototype;

/**
 * @ClassName Shape
 * @Description 原型
 * @Author majinhu
 * @Date 2019/8/15 10:11
 * @Version 1.0
 **/
public abstract class Shape implements Cloneable {

    private String id;
    protected String type;

    abstract void draw();

    public String getType(){
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}