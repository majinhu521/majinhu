package com.study.majinhu.designModel.MediatorPattern;

/**
 * @ClassName User
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:19
 * @Version 1.0
 **/
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name){
        this.name  = name;
    }

    public void sendMessage(String message){
        ChatRoom.showMessage(this,message);
    }
}
