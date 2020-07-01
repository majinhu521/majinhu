package com.study.majinhu.redis.jedis;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName SocketTest
 * @Description socket test
 * @Author majinhu
 * @Date 2020/7/1 14:25
 * @Version 1.0
 **/
public class SocketTest {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("127.0.0.1",6379);
//        socket.getOutputStream().write("abcd".getBytes());
//        socket.close();

//        //键盘输入
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        socket.getOutputStream().write(next.getBytes());
        socket.close();

    }

}
