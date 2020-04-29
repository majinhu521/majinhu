package com.study.majinhu.designModel.proxy.wangmeili;

/**
 * @ClassName Lison
 * @Description 调用方，第三方调用
 * @Author majinhu
 * @Date 2019/7/25 9:15
 * @Version 1.0
 **/
public class Lison {
    public static void main(String[] args) {
        //定义一个女孩，叫王美丽
        Girl girl = new WangMeiLi();
        //约会需要通过她家人的同意。
        WangMeiLiFamily family = new WangMeiLiFamily(girl);
        //找到一个代理对象：她姐姐。
        Girl sister = (Girl) family.getProxyInstance();
        //通过她姐姐，找到了王美丽，和王美丽进行约会。
        sister.yuehui();
        System.out.println("------------------------------------");
        //通过她姐姐，找到了王美丽，和王美丽进行看电影。
        sister.watchMovie();


    }

}
