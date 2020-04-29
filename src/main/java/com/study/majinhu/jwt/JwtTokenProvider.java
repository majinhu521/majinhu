package com.study.majinhu.jwt;

import io.jsonwebtoken.*;

/**
 * @ClassName JwtTokenProvider
 * @Description token生成和解析封装
 * @Author majinhu
 * @Date 2019/7/24 9:02
 * @Version 1.0
 **/
public class JwtTokenProvider {

    static String key;

    public JwtTokenProvider(String key) {
        this.key = key;
    }

    /**
     * 创建token
     * @param claims
     * @return
     */
    public static String createToken(Claims claims){
        String getToken = Jwts.builder().setClaims(claims).compressWith(CompressionCodecs.DEFLATE).
                signWith(SignatureAlgorithm.HS512,key).compact();
        return  getToken;
    }
    /**
     * 解析token
     * @param token
     * @return
     */
    public static Claims parseToken(String token){
        try{
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
         return  null;
    }
    /**
     * 刷新token
     * @param oldToken
     * @return
     */
    public String refToken(String oldToken,Claims newclaims){
        Claims claims = JwtTokenProvider.parseToken(oldToken);
        if(newclaims != null ){
            String newToken = JwtTokenProvider.createToken(newclaims);
            return newToken;
        }
        return null;
    }
}
