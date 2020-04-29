package com.study.majinhu.Concurrent.distributelock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName OrderCodeGenerator
 * @Description 创建订单，6月4日，7月1日动脑学院演示。
 * @Author majinhu
 * @Date 2019/7/2 9:36
 * @Version 1.0
 **/
public class OrderCodeGenerator {
    private int i = 0;
    public String getOrderCode(){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss-");
        return sdf.format(now)+ ++i;
    }
}

