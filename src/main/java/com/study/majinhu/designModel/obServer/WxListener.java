package com.study.majinhu.designModel.obServer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName WxListener
 * @Description
 * @Author majinhu
 * @Date 2019/6/12 8:57
 * @Version 1.0
 **/
@Component
public class WxListener implements ApplicationListener<OrderEvent> {
    @Override
    public void onApplicationEvent(OrderEvent orderEvent) {
        System.out.println("WxListener发送微信"+orderEvent.getSource());
    }
}
