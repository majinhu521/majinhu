package com.study.majinhu.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName SpringbootConfigTest
 * @Description springboot 配置文件中值的获取
 * https://blog.csdn.net/Alias_fa/article/details/89284834
 * @ConfigurationProperties 取值
 * @Value 取值
 * @PropertySource 读取指定配置文件
 * @ImportResource 导入 Spring 的配置文件，使配置文件内容生效
 * @Author majinhu
 * @Date 2020/2/19 10:13
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootConfigTest {

    @Autowired
    Person person;

    @Test
    public void propertiesContextLoads() {
        System.out.println(person);
    }
}
