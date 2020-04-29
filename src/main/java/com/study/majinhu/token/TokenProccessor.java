package com.study.majinhu.token;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName TokenProccessor
 * @Description
 * @Author majinhu
 * @Date 2019/6/25 12:44
 * @Version 1.0
 **/
public class TokenProccessor {

    private TokenProccessor(){};

    private static final TokenProccessor instance = new TokenProccessor();

    public static TokenProccessor getInstance() {
        return instance;
    }
          
    /**
      * 生成Token
      * @return
      */
   public String makeToken() {
            String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
             try {
                MessageDigest md = MessageDigest.getInstance("md5");
                byte md5[] =  md.digest(token.getBytes());
                BASE64Encoder encoder = new BASE64Encoder();
                return encoder.encode(md5);
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             return null;
        }

    public String makeToken2() {
       // String token = generateToken(124l);
        String token = UUID.randomUUID().toString().substring(0,10);
        //String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String generateToken(Long customer_id){
        //10位随机因子
        String random = UUID.randomUUID().toString().substring(0,10);
        //对用户ID和10为随机因子进行MD5加密作为用户Token
        return  getMd5(random+customer_id,true, "UTF-8");
    }
    public static String getMd5(String plainText, boolean md5Format, String charset) {
        String str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.trim().getBytes(charset));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer(32);
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            if (md5Format) {
                str = buf.toString().toUpperCase();
            } else {
                str = buf.toString().substring(8, 24).toUpperCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;

    }

    public static void main(String[] args) {
        System.out.println(TokenProccessor.getInstance().makeToken2());
        System.out.println(generateToken(123L));
    }        
            
}
 