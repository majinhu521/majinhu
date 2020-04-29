package com.study.majinhu.loadbalance;

import java.util.ArrayList;
import java.util.List;

/** https://blog.csdn.net/u013256816/article/details/77131753
 * @ClassName RoundRobin
 * @Description 轮询
 * 将请求按顺序轮流地分配到后端服务器上，
 * 它均衡地对待后端的每一台服务器，而不关系服务器实际的连接数和当前的系统负载。
 * @Author majinhu
 * @Date 2019/12/17 11:02
 * @Version 1.0
 **/
public class RoundRobin {
    private static List<String> list = new ArrayList<String>(){{
        add("192.168.0.2");
        add("192.168.0.3");
        add("192.168.0.4");
    }};
    private static int pos = 0;
    private static final Object lock = new Object();
    public static String getConnectionAddress(){
        String ip = null;
        synchronized (lock) {
            ip = list.get(pos);
            if (++pos >= list.size()) {
                pos = 0;
            }
        }
        return ip;
    }

    public static void main(String[] args) {
        System.out.println(RoundRobin.getConnectionAddress());
        System.out.println(RoundRobin.getConnectionAddress());
        System.out.println(RoundRobin.getConnectionAddress());
        System.out.println(RoundRobin.getConnectionAddress());
        System.out.println(RoundRobin.getConnectionAddress());
        System.out.println(RoundRobin.getConnectionAddress());
    }
}
