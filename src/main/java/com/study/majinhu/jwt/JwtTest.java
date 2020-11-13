package com.study.majinhu.jwt;


import com.study.majinhu.util.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ClassName JwtTest
 * @Description
 * @Author majinhu
 * @Date 2019/7/24 9:14
 * @Version 1.0
 **/
public class JwtTest {
    public static void main(String[] args) throws ParseException {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider("ifyoucanreadthisthenwhatiwanttotellyouisthiskeyisverysecurelol");

        DefaultClaims claims = new DefaultClaims();
        claims.put("userNo","majinhu1");
        claims.put("expiretime", DateUtil.getAfterTime("1000"));
        //自动过期，无需验证，如果过期，解析将抛出过期的异常，无法解析到Claims对象，为null。
        claims.setExpiration( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtil.getAfterTime("1")));
        String token = jwtTokenProvider.createToken(claims);
        System.out.println("生成的token: "+token);

        try {
            Thread.sleep(2000); //模拟过期或者不过期，解析出来的对象是否正确。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //token= token+"123";
        Claims claims1 = jwtTokenProvider.parseToken(token);
        System.out.println("解析出来的token"+claims1);

    }
}
