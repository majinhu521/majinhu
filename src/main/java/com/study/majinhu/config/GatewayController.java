package com.study.majinhu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GatewayController
 * @Description
 * https://blog.csdn.net/dkbnull/article/details/81953190
 * @Author majinhu
 * @Date 2020/2/19 10:35
 * @Version 1.0
 **/

@RestController
public class GatewayController {

    @Value("${person.lastName}")
    private String name;

    @Value("${person.age}")
    private String age;

    @RequestMapping(value = "/gateway")
    public String gateway() {
        return "get properties value by ''@Value'' :" +
                //1、使用@Value注解读取
                " name=" + name +
                " , age=" + age;
    }
}

