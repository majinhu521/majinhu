package com.study.majinhu.redis.jedis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @ClassName RedisServer
 * @Description 模拟reids服务
 * @Author majinhu
 * @Date 2020/7/1 14:17
 * @Version 1.0
 **/
public class RedisServer {
    static  byte[] bs = new byte[1024];
    static ArrayList<Socket> socketArrayList = null;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6379);
        while (true){
            System.out.println("wait connect");
            Socket clientsocket = serverSocket.accept();
            System.out.println("connect ok");
            System.out.println("wait read");
            clientsocket.getInputStream().read(bs);
            System.out.println("read ok");
            System.out.println(new String(bs));
        }
    }


}
