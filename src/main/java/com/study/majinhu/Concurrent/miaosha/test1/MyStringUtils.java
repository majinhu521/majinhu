package com.study.majinhu.Concurrent.miaosha.test1;

import java.util.UUID;

public class MyStringUtils {
    public static String getuuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static String Object2String(Object o) {
        if (o == null) {
            return "";
        } else {
            return o.toString();
        }
    }
}
