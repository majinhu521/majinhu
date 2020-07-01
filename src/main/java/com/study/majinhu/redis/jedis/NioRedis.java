package com.study.majinhu.redis.jedis;

import com.study.majinhu.jdkBase.instanceOf.B;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @ClassName NioRedis
 * @Description NIO实现redis
 * @Author majinhu
 * @Date 2020/7/1 14:46
 * @Version 1.0
 **/
public class NioRedis {
    static ArrayList<SocketChannel> socketList= new ArrayList<>();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        System.out.println("wait connect");
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",6379);
        System.out.println("connect ok");
        serverSocket.bind(socketAddress);
        System.out.println("set configureBlocking false");
        serverSocket.configureBlocking(false);
        System.out.println("set configureBlocking false ok");

        while (true){
            for (SocketChannel socketChannel:socketList) {
                int read = socketChannel.read(byteBuffer);
                if(read>0){
                    System.out.println("read=========="+read);
                    byteBuffer.flip();
                    byte[] bs = new byte[read];
                    byteBuffer.get(bs);
                    String content = new String(bs);
                    System.out.println(content);
                    byteBuffer.flip();
                }
            }
                SocketChannel clientsocket = serverSocket.accept();
                if(clientsocket != null) {
                    System.out.println("conn success======");
                    clientsocket.configureBlocking(false);
                    socketList.add(clientsocket);
                    System.out.println("socket list size ====="+socketList.size());

            }
        }
    }
}
