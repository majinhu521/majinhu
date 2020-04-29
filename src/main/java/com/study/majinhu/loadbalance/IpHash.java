package com.study.majinhu.loadbalance;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IpHash
 * @Description iphash
 * 源地址哈希的思想是根据获取的客户端IP地址，通过哈希函数计算得到的一个数值，
 * 用该数值对服务器列表的大小进行取模运算，得到的结果便是客户端要访问服务器的序号。
 * 采用源地址哈希法进行负载均衡，
 * 同一IP地址的客户端，当后端服务器列表不变时，它每次都会映射到同一台后端服务器进行访问。
 * @Author majinhu
 * @Date 2019/12/17 11:07
 * @Version 1.0
 **/
public class IpHash {
    private static List<String> list = new ArrayList<String>(){{
        add("192.168.0.2"); add("192.168.0.3"); add("192.168.0.4");
    }};
    public static String getConnectionAddress() throws UnknownHostException {
        int ipHashCode = InetAddress.getLocalHost().getHostAddress().hashCode();
        int pos = ipHashCode % list.size();
        return list.get(pos);
    }
    public static void main(String[] args) {
        try {
            System.out.println(IpHash.getConnectionAddress());
            System.out.println(IpHash.getConnectionAddress());
            System.out.println(IpHash.getConnectionAddress());
            System.out.println(IpHash.getConnectionAddress());
            System.out.println(IpHash.getConnectionAddress());
            System.out.println(IpHash.getConnectionAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}

