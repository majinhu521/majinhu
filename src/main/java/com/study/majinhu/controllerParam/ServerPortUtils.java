package com.study.majinhu.controllerParam;



import java.util.Random;

/**
 * @ClassName ServerPortUtils
 * @Description
 * @Author majinhu
 * @Date 2021/3/18 15:40
 * @Version 1.0
 **/
public class ServerPortUtils {
    public static int getAvailablePort() {
        int max = 65535;
        int min = 2000;
        Random random = new Random();
        int port = random.nextInt(max)%(max-min+1) + min;
        boolean using = NetUtils.isLoclePortUsing(port);
        if (using) {
            return getAvailablePort();
        } else {
            return port;
        }
    }
}
