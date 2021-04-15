package com.study.majinhu.optimization.cartnew;

import com.study.majinhu.optimization.cartold.Cart;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CartController
 * @Description 最小的启动的单元。
 *              http://localhost:8088/testCartController2/getCartByUserID
 *
 *可能只修VIP用户购物车的代码，漏了普通用户、内部用户的购物车中重复逻辑实现的相同Bug。
 * 有三个购物车，就需根据不同用户类型使用不同购物车。
 *
 * 就只能不断增加更多的购物车类，写重复的购物车逻辑、写更多if逻辑吗？
 * 当然不是，相同的代码应该只在一处出现！
 *
 * 构秘技之工厂模式 - 消除多if
 * 既然三个购物车都叫XXXUserCart，可将用户类型字符串拼接UserCart构成购物车Bean的名称，然后利用IoC容器，通过Bean的名称直接获取到AbstractCart，调用其process方法即可实现通用。
 *
 * 这就是工厂模式，借助Spring容器实现：
 *
 *
 * 若有新用户类型、用户逻辑，只要新增一个XXXUserCart类继承AbstractCart，实现特殊的优惠和运费处理逻辑即可。
 *
 * 工厂+模板方法模式，消除了重复代码，还避免修改既有代码。这就是设计模式中的开闭原则：对修改关闭，对扩展开放。
 *
 * @Author majinhu
 * @Date 2019/5/30 11:43
 * @Version 1.0
 **/
@Controller
@RequestMapping("/testCartController2")
public class CartController2{

    @Resource
    private ApplicationContext applicationContext;

    @ResponseBody
    @RequestMapping("/getCartByUserID")
    public Cart getCartByUserID(@RequestParam("userId") int userId)   {
        String userCategory = Db.getuserCategory(userId);
        Map<Long, Integer> items = new HashMap<>();
        items.put(123L,1);
        items.put(124L,2);
        items.put(125L,2);
//        if("Normal".equals(userCategory)){
//            NormalUserCart normalUserCart = new NormalUserCart();
//            return normalUserCart.process(userId,items);
//        }
//        if("Vip".equals(userCategory)){
//            VipUserCart VipUserCart = new VipUserCart();
//            return VipUserCart.process(userId,items);
//        }
//        if("Internal".equals(userCategory)){
//            InternaUserCart InternaUserCart = new InternaUserCart();
//            return InternaUserCart.process(userId,items);
//        }
        AbstractCart cart = null;

        cart = (AbstractCart) applicationContext.getBean(userCategory +"UserCartImpl");

        return cart.process(userId,items);
    }

    private static class Db {
        public static String getuserCategory(int userId) {
            return "Normal";
        }
    }

}
