package com.study.majinhu.optimization.cartold;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName GoodsItem
 * @Description
 * @Author majinhu
 * @Date 2021/4/14 17:06
 * @Version 1.0
 **/
@Data
public class GoodsItem {
    private long id;
    //商品数量
    private int quantity;
    //商品单价
    private BigDecimal price;
    //商品优惠
    private BigDecimal couponPrice;
    //商品运费
    private BigDecimal deliveryPrice;
}
