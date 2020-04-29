package com.study.majinhu.designModel.proxy.wangmeili;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * @ClassName WangMeiLi
 * @Description
 * @Author majinhu
 * @Date 2019/7/25 8:54
 * @Version 1.0
 **/
public class WangMeiLi implements Girl{
    @Override
    public void yuehui() {
        System.out.println("王美丽说：很高兴和你约会！");

        //模拟失效的情况。以下调用不能增强。代理找不到。
        //watchMovie();

    }

    @Override
    public void watchMovie() {
        System.out.println("王美丽说：很高兴和你看电影！");
    }
}
