package com.study.majinhu.designModel.StrategyServer;

import org.springframework.stereotype.Service;

/**
 * @ClassName NormalDisCount
 * @Description 普通会员，没折扣
 * @Author majinhu
 * @Date 2019/6/12 15:38
 * @Version 1.0
 **/
@Service
public class NormalDisCount implements  DisCountService {
    @Override
    public String type() {
        return "normal";
    }

    @Override
    public double discount(double fee) {
        return fee;
    }
}
