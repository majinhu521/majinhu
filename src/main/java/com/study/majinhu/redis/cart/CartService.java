package com.study.majinhu.redis.cart;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName CartService
 * @Description
 * @Author majinhu
 * @Date 2021/3/15 10:54
 * @Version 1.0
 **/
@Component
public class CartService {
    Jedis jedis = new Jedis("127.0.0.1",6379);
    //保存购物车到Redis中
      public void insertBuyerCartToRedis(BuyerCart buyerCart, String username){
                List<BuyerItem> items = buyerCart.getItems();
                if (items.size() > 0) {
                        //redis中保存的是skuId 为key , amount 为value的Map集合
                        Map<String, String> hash = new HashMap<String, String>();
                        for (BuyerItem item : items) {
                            //判断是否有同款
                                if (jedis.hexists("buyerCart:"+username, String.valueOf(item.getSku().getId()))) {
                                        jedis.hincrBy("buyerCart:"+username, String.valueOf(item.getSku().getId()), item.getAmount());
                                    }else {
                                        hash.put(String.valueOf(item.getSku().getId()), String.valueOf(item.getAmount()));
                                   }
                            }
                         if (hash.size() > 0) {
                                jedis.hmset("buyerCart:"+username, hash);
                             }
                     }
             }

    //取出Redis中购物车
      public BuyerCart selectBuyerCartFromRedis(String username){
         BuyerCart buyerCart = new BuyerCart();
         //获取所有商品, redis中保存的是skuId 为key , amount 为value的Map集合
         Map<String, String> hgetAll = jedis.hgetAll("buyerCart:"+username);
         Set<Map.Entry<String, String>> entrySet = hgetAll.entrySet();
         for (Map.Entry<String, String> entry : entrySet) {
                 //entry.getKey(): skuId
             Sku sku = new Sku();
                 sku.setId(Long.parseLong(entry.getKey()));
                 BuyerItem buyerItem = new BuyerItem();
                 buyerItem.setSku(sku);
                 //entry.getValue(): amount
                 buyerItem.setAmount(Integer.parseInt(entry.getValue()));
                 //添加到购物车中
                 buyerCart.addItem(buyerItem);
             }
        return buyerCart;
      }

    //向购物车中的购物项 添加相应的数据, 通过skuId 查询sku对象, 颜色对象, 商品对象
     public Sku selectSkuById(Long skuId){
//                 Sku sku = skuDao.selectByPrimaryKey(skuId);
//                 //颜色
//                 sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
//                 //添加商品信息
//                 sku.setProduct(productDao.selectByPrimaryKey(sku.getProductId()));
                 Sku sku = new Sku();
                 sku.setId(skuId);
                 return sku;
             }


    //从购物车中取出指定商品
      public BuyerCart selectBuyerCartFromRedisBySkuIds(String[] skuIds, String username){
                 BuyerCart buyerCart = new BuyerCart();
                 //获取所有商品, redis中保存的是skuId 为key , amount 为value的Map集合
                 Map<String, String> hgetAll = jedis.hgetAll("buyerCart:"+username);
                 if (null != hgetAll && hgetAll.size() > 0) {
                         Set<Map.Entry<String, String>> entrySet = hgetAll.entrySet();
                         for (Map.Entry<String, String> entry : entrySet) {
                                 for (String skuId : skuIds) {
                                        if (skuId.equals(entry.getKey())) {
                                                 //entry.getKey(): skuId
                                                 Sku sku = new Sku();
                                                 sku.setId(Long.parseLong(entry.getKey()));
                                                 BuyerItem buyerItem = new BuyerItem();
                                                 buyerItem.setSku(sku);
                                                 //entry.getValue(): amount
                                                 buyerItem.setAmount(Integer.parseInt(entry.getValue()));
                                                 //添加到购物车中
                                                 buyerCart.addItem(buyerItem);
                                             }
                                     }
                            }
                     }

                 return buyerCart;
             }
}
