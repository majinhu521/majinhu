package com.study.majinhu.runoob;

import java.io.*;
import java.net.Socket;

/**
 * @ClassName GreetingClient
 * @Description https://www.runoob.com/java/java-networking.html
 * Socket 客户端实例
 * @Author majinhu
 * @Date 2019/12/25 13:35
 * @Version 1.0
 **/
public class GreetingClient
{
    public static void main(String [] args)
    {
//        String serverName = args[0];
//        int port = Integer.parseInt(args[1]);
        String serverName = "localhost";
        int port = 6066;
        try
        {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());
            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}