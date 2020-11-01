package com.study.majinhu;

import com.study.majinhu.qrcode.login.QrcodeLogin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MajinhuApplicationTests {
    @Resource
    RedisTemplate stringRedisTemplate;
    @Test
    public void contextLoads() {
        QrcodeLogin qrl = new QrcodeLogin();
        //1. PC生成二维码
        String deviceID = "deviceID392093299037414";
        String qrCodeID = qrl.genQrCodeID(deviceID);
        System.out.println("PC端设备号"+deviceID+"生成二维码"+qrCodeID);
        //2. 手机已扫描二维码待确认,服务端生成临时token
        String mobileToken= "mobileToken23223323222";
        String tempToken =  qrl.genTempToken(mobileToken,qrCodeID);
        System.out.println(mobileToken+"手机已扫描二维码待确认,生成临时token"+tempToken);
        //3. 已确认，根据生成临时token的，点击登录
        String resConfirm = qrl.confirmLoginAndGenPcToken(tempToken);
        System.out.println("点击确认登录完成，返回正式token和状态"+resConfirm);
        //E:\majinhu工具软件copy\redise连接工具\Redis-x64-3.2.100
    }

}
