package com.study.majinhu.designModel.MediatorPattern;

/**
 * @ClassName ChatRoom
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:19
 * @Version 1.0
 **/
import java.util.Date;

public class ChatRoom {
    public static void showMessage(User user, String message){
        System.out.println(new Date().toString()
                + " [" + user.getName() +"] : " + message);
    }
}