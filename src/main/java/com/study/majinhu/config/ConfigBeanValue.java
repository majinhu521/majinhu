package com.study.majinhu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConfigBeanValue
 * @Description
 * @Author majinhu
 * @Date 2020/2/19 10:38
 * @Version 1.0
 **/

@Component
public class ConfigBeanValue {

    @Value("${person.lastName}")
    public String name;

    @Value("${person.age}")
    public String age;
}

