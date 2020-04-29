package com.study.majinhu.designModel.StrategyServer;

import org.springframework.stereotype.Service;

/**
 * @ClassName DisCountService
 * @Description 折扣接口：DisCount
 * @Author majinhu
 * @Date 2019/6/12 15:35
 * @Version 1.0
 **/
@Service
public interface DisCountService {
    //会员类型
    public String type();
    //折扣
    public double discount(double fee);

}
