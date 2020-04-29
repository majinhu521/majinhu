package com.study.majinhu.designModel.obServer;

import com.study.majinhu.MajinhuApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName SpringObserverTest
 * @Description
 * @Author majinhu
 * @Date 2019/6/12 8:59
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MajinhuApplication.class)
public class SpringObserverTest {
    @Autowired
    OrderService orderService;
    /**
     * @Author majinhu
     * @Description 观察者模式测试，订单保存，发送短信，发送邮件，发送微信。。。。。
     * @Date 15:24 2019/6/12
     * @Param []
     * @return void
     **/
    @Test
    public void testObserver(){
        orderService.saveOrder("MAJINHU");
    }
}
