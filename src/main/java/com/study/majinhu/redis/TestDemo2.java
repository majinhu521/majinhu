package com.study.majinhu.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName TestDemo2
 * @Description
 * @Author majinhu
 * @Date 2019/8/9 9:24
 * @Version 1.0
 **/
public class TestDemo2 {

    @Autowired
    RedisService redislock;
    long TIMEOUT =3000;

    @Transactional
    public void orderProductMockDiffUser(String productId){
        long time = System.currentTimeMillis()+TIMEOUT;
        if(!redislock.lock(productId,String.valueOf(time))){
           System.out.println("获取锁失败");
        }
//        //1.查库存
//        int stockNum  = stock.get(productId);
//        if(stocknum == 0){
//            throw new SellException(ProductStatusEnum.STOCK_EMPTY);
//            //这里抛出的异常要是运行时异常，否则无法进行数据回滚，这也是spring中比较基础的
//        }else{
//            //2.下单
//            orders.put(KeyUtil.genUniqueKey(),productId);//生成随机用户id模拟高并发
//            sotckNum = stockNum-1;
//            try{
//                Thread.sleep(100);
//            } catch (InterruptedExcption e){
//                e.printStackTrace();
//            }
//            stock.put(productId,stockNum);
//        }
        redislock.unlock(productId,String.valueOf(time));
    }
}
