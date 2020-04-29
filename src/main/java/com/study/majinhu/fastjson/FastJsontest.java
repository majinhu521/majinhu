package com.study.majinhu.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName FastJsontest
 * @Description
 * https://www.w3cschool.cn/fastjson/fastjson-quickstart.html
 * String text = JSON.toJSONString(obj); //序列化
 * VO vo = JSON.parseObject("{...}", VO.class); //反序列化
 * @Author majinhu
 * @Date 2020/2/26 15:57
 * @Version 1.0
 **/
public class FastJsontest {

    public static void main(String[] args) {
        Group group = new Group();
        group.setCode("1001");
        group.setName("集团公司1");
        String jsonString = JSON.toJSONString(group);//序列化
        System.out.println(jsonString);
        Group group2 = JSON.parseObject("{\"code\":\"1001\",\"name\":\"集团公司1\"}", Group.class); //反序列化
        System.out.println(group2.toString());
        System.out.println(group2.getCode());
        System.out.println(group2.getName());
    }

}
