package com.study.majinhu.optimization.cartold;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CartController
 * @Description 最小的启动的单元。
 *              http://localhost:8088/testCartController/getCartByUserID
 *
 *可能只修VIP用户购物车的代码，漏了普通用户、内部用户的购物车中重复逻辑实现的相同Bug。
 * 有三个购物车，就需根据不同用户类型使用不同购物车。
 *
 * 就只能不断增加更多的购物车类，写重复的购物车逻辑、写更多if逻辑吗？
 * 当然不是，相同的代码应该只在一处出现！
 *
 * @Author majinhu
 * @Date 2019/5/30 11:43
 * @Version 1.0
 **/
@Controller
@RequestMapping("/testCartController")
public class CartController {
    @ResponseBody
    @RequestMapping("/getCartByUserID")
    public Cart getCartByUserID(@RequestParam("userId") int userId){
        String userCategory = Db.getuserCategory(userId);
        Map<Long, Integer> items = new HashMap<>();
        items.put(123L,1);
        items.put(124L,2);
        items.put(125L,2);
        if("Normal".equals(userCategory)){
            NormalUserCart normalUserCart = new NormalUserCart();
            return normalUserCart.process(userId,items);
        }
        if("Vip".equals(userCategory)){
            VipUserCart VipUserCart = new VipUserCart();
            return VipUserCart.process(userId,items);
        }
        if("Internal".equals(userCategory)){
            InternaUserCart InternaUserCart = new InternaUserCart();
            return InternaUserCart.process(userId,items);
        }
        return null;
    }

    private static class Db {
        public static String getuserCategory(int userId) {
            return "Normal";
        }
    }

}
