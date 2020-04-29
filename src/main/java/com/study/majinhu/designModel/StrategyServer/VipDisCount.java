package com.study.majinhu.designModel.StrategyServer;

import org.springframework.stereotype.Service;

/**
 * @ClassName VipDisCount
 * @Description vip折扣
 * @Author majinhu
 * @Date 2019/6/12 15:40
 * @Version 1.0
 **/
@Service
public class VipDisCount implements  DisCountService{
    @Override
    public String type() {
        return "vip";
    }

    @Override
    public double discount(double fee) {
        return 0.9*fee;
    }
}
