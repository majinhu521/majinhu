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

    @Test
    public void test() throws InterruptedException{
        testService.testAnnotation();
        Thread.sleep(10000);
    }
}
