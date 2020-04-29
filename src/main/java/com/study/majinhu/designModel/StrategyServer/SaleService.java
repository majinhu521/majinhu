package com.study.majinhu.designModel.StrategyServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName SaleService
 * @Description 电商销售，不同的类型，不同的折扣。
 * @Author majinhu
 * @Date 2019/6/12 15:27
 * @Version 1.0
 **/
@Service
public class SaleService {
    /**
     * @Author majinhu
     * @Description 老方法一：不同会员打折信息。
     * @Date 15:34 2019/6/12
     * @Param [type, fee]
     * @return double
     **/
    public double sale(String type ,double fee) {
        //老式方法：根据不同的类型判断不同的折扣信息。
        if ("normal".equals(type)) {
            return 1.0 *fee;
        } else if ("vip".equals(type)) {
            return 0.9 *fee;
        }else if ("baijin_vip".equals(type)) {
            return 0.8 *fee;
        }else if ("zhuanshi_vip".equals(type)) {
            return 0.7 *fee;
        }
        return fee;
    }

    @Autowired
    NormalDisCount normalDisCount;

    @Autowired
    VipDisCount vipDisCount;
    /**
     * @Author majinhu
     * @Description 新方法1：根据不同的类型，注入不同的策略。但是没有脱离if判断。
     * @Date 15:34 2019/6/12
     * @Param [type, fee]
     * @return double
     **/
    public double sale2(String type ,double fee) {
        //老式方法：根据不同的类型判断不同的折扣信息。
        if ("normal".equals(type)) {
            return normalDisCount.discount(fee);
        } else if ("vip".equals(type)) {
            return vipDisCount.discount(fee);
        }else if ("baijin_vip".equals(type)) {
            return normalDisCount.discount(fee);
        }else if ("zhuanshi_vip".equals(type)) {
            return normalDisCount.discount(fee);
        }
        return fee;
    }

    //通过hashMap方式实现对type的封装，减少if判断。把接口作为值放入hashMap中
    HashMap<String,DisCountService> serviceHashMap = new HashMap<>();
    //spring 注入，注入所有类型。所有实现类commont已经装配上了。所以把接口可以放入到list中。
    public SaleService(List<DisCountService> disCountServices){
        for (DisCountService disCountService : disCountServices) {
            serviceHashMap.put(disCountService.type(),disCountService);
        }
    }
    /**
     * @Author majinhu
     * @Description HashMap取得对应的type和对应的折扣。
     * @Date 15:54 2019/6/12
     * @Param [type, fee]
     * @return double
     **/
    public double sale3(String type ,double fee) {
        return serviceHashMap.get(type).discount(fee);
    }

}
