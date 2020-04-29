package com.study.majinhu.designModel.StrategyServer;

import com.study.majinhu.MajinhuApplication;
import com.study.majinhu.designModel.obServer.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName SpringStragyserverTest
 * @Description 策略模式测试
 * @Author majinhu
 * @Date 2019/6/12 8:59
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MajinhuApplication.class)
public class SpringStragyserverTest {
    @Autowired
    SaleService saleService;
    /**
     * @Author majinhu
     * @Description 
     * @Date 15:24 2019/6/12
     * @Param []
     * @return void
     **/
    @Test
    public void test(){
        //VIP 类型，折扣前为100
        double vip = saleService.sale3("vip", 100);
        System.out.println(vip);
    }
}
