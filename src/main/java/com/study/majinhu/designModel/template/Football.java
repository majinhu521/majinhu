package com.study.majinhu.designModel.template;

/**
 * @ClassName Football
 * @Description
 * @Author majinhu
 * @Date 2019/7/25 14:04
 * @Version 1.0
 **/
public class Football extends Game {

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }
}
