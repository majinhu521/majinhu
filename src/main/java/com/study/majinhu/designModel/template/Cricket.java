package com.study.majinhu.designModel.template;

/**
 * @ClassName Cricket
 * @Description
 * @Author majinhu
 * @Date 2019/7/25 14:03
 * @Version 1.0
 **/
public class Cricket extends Game {

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }
}