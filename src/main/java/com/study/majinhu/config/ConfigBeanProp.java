package com.study.majinhu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConfigBeanProp
 * @Description 使用@ConfigurationProperties注解读取
 *
 * @Component 表示将该类标识为Bean
 *
 * @ConfigurationProperties(prefix = "demo")用于绑定属性，其中prefix表示所绑定的属性的前缀。
 *
 * @PropertySource(value = "config.properties")表示配置文件路径。
 * ————————————————
 * 版权声明：本文为CSDN博主「dkbnull」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/dkbnull/article/details/81953190
 * @Author majinhu
 * @Date 2020/2/19 10:47
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "demo")
@PropertySource(value = "demo.properties")
public class ConfigBeanProp {

    private String phone;

    private String wife;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

}
