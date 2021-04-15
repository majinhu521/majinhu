package com.study.majinhu.optimization.cartold;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NormalUserCart
 * @Description 普通用户
 * https://developer.aliyun.com/article/776399?spm=a2c6h.12873581.0.0.b6ac298aiGMsNK&groupCode=javaup
 * @Author majinhu
 * @Date 2021/4/14 17:10
 * @Version 1.0
 **/
public class NormalUserCart {
    public Cart process(long userId, Map<Long, Integer> items) {
        Cart cart = new Cart();

        List<GoodsItem> itemsList = new ArrayList<>();
        items.forEach((key, value) -> {
            GoodsItem item = new GoodsItem();
            item.setId(key);
            item.setPrice(Db.getItemPrice(key));
            item.setQuantity(value);
            itemsList.add(item);
        });
        cart.setItems(itemsList);

        itemsList.forEach(item ->{
            //运费 商品总价的0.1
            item.setDeliveryPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
            .multiply(new BigDecimal("0.1")));
            //优惠 0
            item.setCouponPrice(BigDecimal.ZERO);
        });

        //计算商品总价
        cart.setTotalItemPrice(cart.getItems().stream().map(item->
            item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add));

        //计算总运费
        cart.setTotalDeliveryPrice(cart.getItems().stream().map(GoodsItem::getDeliveryPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add));
        //计算总优惠
        cart.setTotalDiscount(cart.getItems().stream().map(GoodsItem::getCouponPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add));
        //计算支付价 =商品总价+总运费-总优惠
        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
        return cart;
    }

    private static class Db {
        public static BigDecimal getItemPrice(Long key) {
            return BigDecimal.valueOf(1);
        }
    }
}
