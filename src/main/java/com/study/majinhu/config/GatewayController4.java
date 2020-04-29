package com.study.majinhu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName GatewayController4
 * @Description 使用@ConfigurationProperties注解读取
 * @Author majinhu
 * @Date 2020/2/19 10:49
 * @Version 1.0
 **/
public class GatewayController4 {
    @Autowired
    private ConfigBeanValue configBeanValue;

    @Autowired
    private Environment environment;

    @Autowired
    private ConfigBeanProp configBeanProp;

    @RequestMapping(value = "/gateway")
    public String gateway() {
        return "get properties value by ''@Value'' :" +
                //1、使用@Value注解读取
                " name=" + configBeanValue.name +
                " , age=" + configBeanValue.age +
                "<p>get properties value by ''Environment'' :" +
                //2、使用Environment读取
                " sex=" + environment.getProperty("demo.sex") +
                " , address=" + environment.getProperty("demo.address") +
                "<p>get properties value by ''@ConfigurationProperties'' :" +
                //3、使用@ConfigurationProperties注解读取
                " phone=" + configBeanProp.getPhone() +
                " , wife=" + configBeanProp.getWife();
    }


}
