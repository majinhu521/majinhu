package com.study.majinhu.optimization.cartnew;


import com.study.majinhu.optimization.cartold.GoodsItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @ClassName InternaUserCart
 * @Description 内部用户
 * https://developer.aliyun.com/article/776399?spm=a2c6h.12873581.0.0.b6ac298aiGMsNK&groupCode=javaup
 * @Author majinhu
 * @Date 2021/4/14 17:10
 * @Version 1.0
 **/
@Service("InternaUserCartImpl")
public class InternaUserCartImpl extends AbstractCart{
    //运费0
    @Override
    protected void processDeliveryPrice(long userId, GoodsItem item) {
        item.setDeliveryPrice(BigDecimal.ZERO);
    }
    //优惠 0
    @Override
    protected void processCouponPrice(long userId, GoodsItem item) {
        item.setCouponPrice(BigDecimal.ZERO);
    }

}
