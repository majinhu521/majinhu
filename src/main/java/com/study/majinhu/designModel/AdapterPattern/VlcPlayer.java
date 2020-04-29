package com.study.majinhu.designModel.AdapterPattern;

/**
 * @ClassName VlcPlayer
 * @Description
 * @Author majinhu
 * @Date 2019/7/5 10:28
 * @Version 1.0
 **/
public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}