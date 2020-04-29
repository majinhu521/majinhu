package com.study.majinhu.runoob;

/**
 * @ClassName URLDemo
 * @Description 使用java.net的URL类获取URL的各个部分参数：
 * https://www.runoob.com/java/java-url-processing.html
 * @Author majinhu
 * @Date 2019/12/25 13:25
 * @Version 1.0
 **/
import java.net.*;
import java.io.*;


public class URLDemo
{
    public static void main(String [] args)
    {
        try
        { //URL 类方法
            URL url = new URL("https://www.runoob.com/index.html?language=cn#j2se");
            System.out.println("URL 为：" + url.toString());
            System.out.println("协议为：" + url.getProtocol());
            System.out.println("验证信息：" + url.getAuthority());
            System.out.println("文件名及请求参数：" + url.getFile());
            System.out.println("主机名：" + url.getHost());
            System.out.println("路径：" + url.getPath());
            System.out.println("端口：" + url.getPort());
            System.out.println("默认端口：" + url.getDefaultPort());
            System.out.println("请求参数：" + url.getQuery());
            System.out.println("定位位置：" + url.getRef());
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }


}

 class URLConnDemo
{
    public static void main(String [] args)
    {
        try
        {
            URL url = new URL("http://www.runoob.com");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if(urlConnection instanceof HttpURLConnection)
            {
                connection = (HttpURLConnection) urlConnection;
            }
            else
            {
                System.out.println("请输入 URL 地址");
                return;
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current;
            while((current = in.readLine()) != null)
            {
                urlString += current;
            }
            System.out.println(urlString);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}

