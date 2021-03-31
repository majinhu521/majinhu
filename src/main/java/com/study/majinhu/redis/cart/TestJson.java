package com.study.majinhu.redis.cart;

/**
 * @ClassName TestJson
 * @Description
 * @Author majinhu
 * @Date 2021/3/15 10:44
 * @Version 1.0
 **/

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.majinhu.jdkBase.JDK18new.Student;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

/**
 * 将对象转换成json字符串/json字符串转成对象
 * 我们在这里先写一个小的Demo来演示json和对象之间的互转, 这里使用到了springmvc中的ObjectMapper类.
 *
 * 我们使用了Include.NON_NULL, 如果TestTb 中属性为null 的就不给转换成Json, 从对象-->Json字符串  用的是 objectMapper.writeValue(). 从Json字符串-->对象使用的是objectMapper.readValue().
 * 回归上面我们项目中的代码, 只有未登录 添加商品时才会将此商品添加到Cookie中.
 *
 * 将对象购物车对象buyerCart转换成了Json格式.
 * 将商品添加到购物车, 不管是登录还是未登录, 都要先取出Cookie中的购物车, 然后将当前选择的商品追加到购物车中.
 * 然后登录的话  就把Cookie中的购物车清空, 并将购物车的内容添加到Redis中做持久化保存.
 * 如果未登录, 将选择的商品追加到Cookie中.
 */
 public class TestJson {

    @Test
     public void testAdd() throws Exception {
        Student testTb = new Student();
        testTb.setName("范冰冰");
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
         //将对象转换成json字符串
        Writer wr = new StringWriter();
        om.writeValue(wr, testTb);
        System.out.println(wr.toString());

        //转回对象
        Student r = om.readValue(wr.toString(), Student.class);
        System.out.println(r.toString());
    }

 }