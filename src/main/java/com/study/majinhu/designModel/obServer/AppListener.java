package com.study.majinhu.designModel.obServer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName AppListener
 * @Description 观察者模式：定义有序监听器,需要@Component，实现SmartApplicationListener接口即可。
 * @Author majinhu
 * @Date 2019/6/12 9:59
 * @Version 1.0
 **/
@Component
public  class AppListener implements SmartApplicationListener {
    /**
     * @Author majinhu
     * @Description 监听的类名:supportsEventType用于指定支持的事件类型，只有支持的才调用onApplicationEvent；
     * @Date 14:39 2019/6/12
     * @Param [aClass]
     * @return boolean
     **/
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == OrderEvent.class;
    }
    /**
     * @Author majinhu
     * @Description  supportsSourceType：支持的目标类型，只有支持的才调用onApplicationEvent；
     * @Date 14:39 2019/6/12
     * @Param [sourceType]
     * @return boolean
     **/
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }
    /**
     * @Author majinhu
     * @Description getOrder：即顺序，越小优先级越高
     * @Date 14:41 2019/6/12
     * @Param []
     * @return int
     **/
    @Override
    public int getOrder() {
        return 1;
    }

    /**
     * @Author majinhu
     * @Description 业务处理
     * @Date 14:41 2019/6/12
     * @Param [applicationEvent]
     * @return void
     **/
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("调用app接口1"+applicationEvent.getSource());
    }
}
