package com.study.majinhu.limitTimeOder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName DlyOrderProcessor
 * @Description 限时订单处理类
 * https://www.jianshu.com/p/e92f4d67d23d
 * http://www.studyshare.cn/blog/details/1132/1
 * @Author majinhu
 * @Date 2019/12/18 10:42
 * @Version 1.0
 **/
@Service
public class DlyOrderProcessor {
    private Logger logger = LoggerFactory.getLogger(DlyOrderProcessor.class);
    //检查数据库中指定id的订单状态，如果为未支付，则修改为已过期。
    public void checkDelayOder(Order order){
        //根据订单id查询数据库，注入dao
        //0-未支付，1-已支付。-1 已过期，关闭。
        Order order1 = new Order();
        if("0".equals(order1.getStatus()))
        {
            logger.info("订单未支付已过期。");
            //Todo
            // db update status =-1 id=order1.getId()
        }else{
            logger.info("订单已支付，无需修改。");
        }
    }
}
