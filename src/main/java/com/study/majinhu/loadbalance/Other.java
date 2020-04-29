package com.study.majinhu.loadbalance;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Other
 * @Description
 * 加权轮询法
 * 不同的后端服务器可能机器的配置和当前系统的负载并不相同，因此它们的抗压能力也不相同。给配置高、负载低的机器配置更高的权重，让其处理更多的请求；而配置低、负载高的集群，给其分配较低的权重，降低其系统负载，加权轮询能很好地处理这一问题，并将请求顺序且按照权重分配到后端。
 *
 * 加权随机法
 * 与加权轮询法一样，加权随机法也根据后端机器的配置、系统的负载分配不同权重。不同的是，它是按照权重随机请求后端服务器，而非顺序。
 *
 * 最小连接数法
 * 最小连接数算法比较灵活和智能，由于后端服务器的配置不尽相同，对于请求的处理有块有慢，它是根据后端服务器当前的连接情况，动态地选取其中当前积压连接数最少的一台服务器来处理当前的请求，尽可能地提高后端服务的利用效率，将负载合理地分流到每一台服务器。
 *
 * 有关于加权轮询法、加权随机法和最小连接数法的实现也比较简单，这里就留给读者自己动手实践一下。
 * ————————————————
 * 版权声明：本文为CSDN博主「朱小厮」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/u013256816/article/details/77131753
 *
 * https://www.cnblogs.com/wsw-seu/p/11336634.html
 *
 * @Author majinhu
 * @Date 2019/12/17 11:11
 * @Version 1.0
 **/
public class Other {
    private static List<String> list = new ArrayList<String>(){{
        add("192.168.0.2");
        add("192.168.0.3");
        add("192.168.0.4");
    }};
    private static double quan1 = 0.5;
    private static double quan2 = 0.2;
    private static double quan3 = 0.3;

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
