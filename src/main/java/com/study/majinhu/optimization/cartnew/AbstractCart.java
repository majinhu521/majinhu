package com.study.majinhu.optimization.cartnew;

import com.study.majinhu.optimization.cartold.Cart;
import com.study.majinhu.optimization.cartold.GoodsItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AbstractCart
 * @Description
 *  重构秘技 - 模板方法模式
 * 可以把重复逻辑定义在抽象类，三个购物车只要分别实现不同部分的逻辑。
 * 这其实就是模板方法模式。
 * 在父类中实现购物车处理的流程模板，然后把需要特殊处理的留抽象方法定义，让子类去实现。由于父类逻辑无法单独工作，因此需要定义为抽象类。
 *
 * 如下代码所示，AbstractCart抽象类实现了购物车通用的逻辑，额外定义了两个抽象方法让子类去实现。其中，processCouponPrice方法用于计算商品折扣，processDeliveryPrice方法用于计算运费。
 * @Author majinhu
 * @Date 2021/4/15 9:28
 * @Version 1.0
 **/
public abstract class AbstractCart {

    public Cart process(long userId, Map<Long, Integer> items) {
        Cart cart = new Cart();

        List<GoodsItem> itemsList = new ArrayList<>();
        items.forEach((key, value) -> {
            GoodsItem item = new GoodsItem();
            item.setId(key);
            item.setPrice(Db1.getItemPrice(key));
            item.setQuantity(value);
            itemsList.add(item);
        });
        cart.setItems(itemsList);

        itemsList.forEach(item ->{
            //运费
            processDeliveryPrice(userId,item);
            //优惠
            processCouponPrice(userId,item);

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

    protected abstract void processDeliveryPrice(long userId, GoodsItem item);
    protected abstract void processCouponPrice(long userId, GoodsItem item);

    private static class Db1 {
        public static BigDecimal getItemPrice(Long key) {
            return BigDecimal.valueOf(1);
        }
    }


}
