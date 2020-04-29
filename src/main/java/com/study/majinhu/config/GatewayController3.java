package com.study.majinhu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GatewayController3
 * @Description
 * https://blog.csdn.net/dkbnull/article/details/81953190
 * @Author majinhu
 * @Date 2020/2/19 10:38
 * @Version 1.0
 **/
@RestController
public class GatewayController3 {

    @Autowired
    private ConfigBeanValue configBeanValue;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/gateway")
    public String gateway() {
        return "get properties value by ''@Value'' :" +
                //1、使用@Value注解读取
                " name=" + configBeanValue.name +
                " , age=" + configBeanValue.age +
                "<p>get properties value by ''Environment'' :" +
                //2、使用Environment读取
                " , sex=" + environment.getProperty("demo.sex") +
                " , address=" + environment.getProperty("demo.address");
    }

}
