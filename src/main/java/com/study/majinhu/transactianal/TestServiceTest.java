package com.study.majinhu.transactianal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName TestServiceTest
 * @Description
 * @Author majinhu
 * @Date 2020/3/5 15:54
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Autowired
    private TestService2 testService2;


    @Test
    public void test() throws InterruptedException{
        //失效方式
//        testService.testAnnotation();
        //正确方式1
//        testService.testAnnotation2();
        //正确方式2
        testService2.testAnnotation();
        Thread.sleep(30000);
    }
}
