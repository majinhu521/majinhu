package com.study.majinhu.controllerParam;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @ClassName NetUtils
 * @Description https://blog.csdn.net/lzx1991610/article/details/108095939
 * @Author majinhu
 * @Date 2021/3/18 15:47
 * @Version 1.0
 **/
public class NetUtils {
    /***
     *  true:already in using  false:not using
     * @param port
     */
    public static boolean isLoclePortUsing(int port){
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }


    /***
     *  true:already in using  false:not using
     * @param host
     * @param port
     * @throws UnknownHostException
     */
    public static boolean isPortUsing(String host,int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(theAddress,port);
            flag = true;
        } catch (IOException e) {

        }
        return flag;
    }


}
