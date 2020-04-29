package com.study.majinhu.designModel.obServer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * @ClassName OrderEvent
 * @Description 创建订单事件，其他监听这个事件
 * https://blog.csdn.net/badfraud/article/details/90601271
 * @Author majinhu
 * @Date 2019/6/12 8:48
 * @Version 1.0
 **/
public class OrderEvent extends ApplicationEvent {
    //构造方法，Object输入对象
    public OrderEvent(Object source) {
        super(source);
    }
}
