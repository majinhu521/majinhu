package com.study.majinhu.designModel.AdapterPattern;

/**
 * @ClassName Mp4Player
 * @Description
 * @Author majinhu
 * @Date 2019/7/5 10:28
 * @Version 1.0
 **/
public class Mp4Player implements AdvancedMediaPlayer{

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}