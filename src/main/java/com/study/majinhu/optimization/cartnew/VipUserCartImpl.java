package com.study.majinhu.optimization.cartnew;


import com.study.majinhu.optimization.cartold.GoodsItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @ClassName VipUserCart
 * @Description vip用户
 * https://developer.aliyun.com/article/776399?spm=a2c6h.12873581.0.0.b6ac298aiGMsNK&groupCode=javaup
 * @Author majinhu
 * @Date 2021/4/14 17:10
 * @Version 1.0
 **/
@Service("VipUserCartImpl")
public class VipUserCartImpl extends NormalUserCartImpl {

    // item.setDeliveryPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
    //                    .multiply(new BigDecimal("0.1")));
    //            //优惠 0 ,数量大于2件享受一定折扣。
    //            if (item.getQuantity() > 2) {
    //                item.setCouponPrice(item.getPrice()
    //                        .multiply(BigDecimal.valueOf(100-Db.getUserCouponPercent(userId)))
    //                        .divide(BigDecimal.valueOf(100))
    //                        .multiply(BigDecimal.valueOf(item.getQuantity()-2)));
    //            } else {
    //                item.setCouponPrice(BigDecimal.ZERO);
    //            }

//    @Override
//    protected void processDeliveryPrice(long userId, GoodsItem item) {
//        item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
//                            .multiply(new BigDecimal("0.1"));
//    }

    @Override
    protected void processCouponPrice(long userId, GoodsItem item) {
        if (item.getQuantity() > 2) {
            item.setCouponPrice(item.getPrice()
                    .multiply(BigDecimal.valueOf(100-Db.getUserCouponPercent(userId)))
                    .divide(BigDecimal.valueOf(100))
                    .multiply(BigDecimal.valueOf(item.getQuantity()-2)));
        } else {
            item.setCouponPrice(BigDecimal.ZERO);
        }
    }

    private static class Db {
        public static BigDecimal getItemPrice(Long key) {
            return BigDecimal.valueOf(1);
        }
        public static int getUserCouponPercent(Long userId) {
            return 2;
        }
    }
}
