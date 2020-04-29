package com.study.majinhu.designModel.template;

/**
 * @ClassName Game
 * @Description
 * @Author majinhu
 * @Date 2019/7/25 13:59
 * @Version 1.0
 **/
public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    //模板
    public final void play(){

        //初始化游戏
        initialize();

        //开始游戏
        startPlay();

        //结束游戏
        endPlay();
    }
}
