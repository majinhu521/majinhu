package com.study.majinhu.designModel.template;

/**
 * @ClassName TemplatePatternDemo
 * @Description 模板模式
 * @Author majinhu
 * @Date 2019/7/25 14:05
 * @Version 1.0
 **/
public class TemplatePatternDemo {
    public static void main(String[] args) {

        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}