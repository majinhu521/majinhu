package com.study.majinhu.designModel.AdapterPattern;

/**
 * @ClassName MediaAdapter
 * @Description
 * 实现了 MediaPlayer 接口的适配器类 MediaAdapter，
 * 并使用 AdvancedMediaPlayer 对象来播放所需的格式。
 * @Author majinhu
 * @Date 2019/7/5 10:29
 * @Version 1.0
 **/
public class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc") ){
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}