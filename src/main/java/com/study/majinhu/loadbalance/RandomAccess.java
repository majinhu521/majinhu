package com.study.majinhu.loadbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName RandomAccess
 * @Description 随机
 * 通过随机算法，根据后端服务器的列表大小值来随机选取其中的一台服务器进行访问。
 * 由概率统计理论可以得知，随着客户端调用服务端的次数增多，
 * 其实际效果越来越接近于平均分配调用量到后端的每一台服务器，也就是轮询的结果。对应的示例代码如下：
 * @Author majinhu
 * @Date 2019/12/17 11:06
 * @Version 1.0
 **/
public class RandomAccess {
    private static List<String> list = new ArrayList<String>(){{
        add("192.168.0.2");
        add("192.168.0.3");
        add("192.168.0.4");
    }};
    public static String getConnectionAddress(){
        Random random = new Random();
        int pos = random.nextInt(list.size());
        return list.get(pos);
    }

    public static void main(String[] args) {
        System.out.println(RandomAccess.getConnectionAddress());
        System.out.println(RandomAccess.getConnectionAddress());
        System.out.println(RandomAccess.getConnectionAddress());
        System.out.println(RandomAccess.getConnectionAddress());
        System.out.println(RandomAccess.getConnectionAddress());
        System.out.println(RandomAccess.getConnectionAddress());
    }
}
