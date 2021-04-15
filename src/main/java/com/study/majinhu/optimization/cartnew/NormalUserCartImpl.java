package com.study.majinhu.optimization.cartnew;


import com.study.majinhu.optimization.cartold.GoodsItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @ClassName NormalUserCart
 * @Description 普通用户
 * https://developer.aliyun.com/article/776399?spm=a2c6h.12873581.0.0.b6ac298aiGMsNK&groupCode=javaup
 * @Author majinhu
 * @Date 2021/4/14 17:10
 * @Version 1.0
 **/
@Service("NormalUserCartImpl")
public class NormalUserCartImpl extends AbstractCart {

    //      //运费 商品总价的0.1
    //            item.setDeliveryPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
    //            .multiply(new BigDecimal("0.1")));
    //            //优惠 0
    //            item.setCouponPrice(BigDecimal.ZERO);
    @Override
    protected void processDeliveryPrice(long userId, GoodsItem item) {
        item.setDeliveryPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                            .multiply(new BigDecimal("0.1")));
    }

    @Override
    protected void processCouponPrice(long userId, GoodsItem item) {
        item.setCouponPrice(BigDecimal.ZERO);
    }
}
