package com.study.majinhu.optimization.cartold;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * https://developer.aliyun.com/article/776399?spm=a2c6h.12873581.0.0.b6ac298aiGMsNK&groupCode=javaup
 * @ClassName Cart
 * @Description
 *  需求
 * 开发购物车下单，对不同用户不同处理：
 *
 * 普通用户需要收取运费，运费是商品价格的10%，无商品折扣
 * VIP用户同样需要收取商品价格10%的快递费，但购买两件以上相同商品时，第三件开始享受一定折扣
 * 内部用户可以免运费，无商品折扣
 * 实现三种类型的购物车业务逻辑，把入参Map对象（K：商品ID，V：商品数量），转换为出参购物车类型Cart。
 * @Author majinhu
 * @Date 2021/4/14 16:58
 * @Version 1.0
 **/
@Data
public class Cart {
    /**
     * 商品清单
     */
    private List<GoodsItem> Items = new ArrayList<>();

    /**
     * 总折扣
     */
    private BigDecimal totalDiscount;

    /**
     * 商品总价
     */
    private BigDecimal totalItemPrice;

    /**
     * 运费
     */
    private BigDecimal totalDeliveryPrice;

    /**
     * 应付总价
     */
    private BigDecimal payPrice;
}
