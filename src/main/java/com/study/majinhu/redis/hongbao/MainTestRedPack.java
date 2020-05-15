package com.study.majinhu.redis.hongbao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName MainTestRedPack
 * @Description main https://blog.csdn.net/a1173537204/java/article/details/103229804
 * @Author majinhu
 * @Date 2020/5/15 15:10
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MainTestRedPack {
    @Test
    public static void main(String[] args) throws InterruptedException {

        GenRedPack.genHongBao();//初始化红包

        GetRedPack.getHongBao();//从红包池抢红包

    }

}
