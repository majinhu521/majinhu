package com.study.majinhu.qrcode.login;

import com.study.majinhu.rsatest.StringUtils;
import com.study.majinhu.token.TokenProccessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName QrcodeLogin
 * @Description 二维码登录
 * https://www.cnblogs.com/jamaler/p/12610349.html
 * https://www.cnblogs.com/zqzjs/p/6742562.html
 * https://wx.qq.com/
 * @Author majinhu
 * @Date 2020/11/1 9:02
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class QrcodeLogin {

    private String  qrcodeStart;
    private String  qrcodeEnd;
    private String  qrcodeStatus; //0待扫描、1已扫描待确认、2已确认。

    @Resource
    RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void genQrCodeID1(){
        String  deviceID ="123";
        String qrCodeID = TokenProccessor.generateToken(deviceID);
        redisTemplate.opsForValue().set(qrCodeID,"0",5*60, TimeUnit.MILLISECONDS);
        System.out.println("redis设置了qrCodeID"+redisTemplate.opsForValue().get(qrCodeID));
    }

    /** 待扫描
     * 1.PC端发送二维码请求,携带pc设备信息deviceID
     * 服务端生成二维码，与设备信息deviceID进行绑定。
     * @param deviceID e392093299037414
     * @return
     */
    public String genQrCodeID(String deviceID){
        String qrCodeID = "qrCodeID"+TokenProccessor.generateToken(deviceID);
        redisUtil.set(qrCodeID,"0",5*60);
        return qrCodeID;
    }

    /** 已扫描待确认
     * 2.手机将身份信息mobileToken 和二维码信息qrCodeID发送服务端。
     * 服务端生成临时token，将mobileToken 和 qrCodeID 进行绑定。
     *  绑定关系存入redis，临时token作为key，mobileToken 和 qrCodeID 作为值。
     * @param mobileToken  手机已登录的token
     * @param qrCodeID  二维码id
     * @return
     */
    public String genTempToken(String mobileToken,String qrCodeID){
        String tempToken = "tempToken"+TokenProccessor.generateToken(mobileToken+qrCodeID);
        redisUtil.set(qrCodeID,"1",5*60);
        //临时token作为key，mobileToken 和 qrCodeID 作为值。临时token确认时间5分钟。
        redisUtil.set(tempToken,mobileToken+"|"+qrCodeID,5*60);
        return tempToken;
    }

    /** 已确认
     * 3.手机端携带临时token，确认登录
     * 服务端生成PC端token，返回二维码状态为已登录。
     * @param tempToken
     * @return
     */
    public String confirmLoginAndGenPcToken(String tempToken){
        //根据临时token查询出对应的qrCodeID。更改二维码状态。
        String qrCodeID = queryQrCodeID(tempToken);
        if(StringUtils.empty(qrCodeID)){
            System.out.println("登陆确认时间已超时，请重新登陆");
        }else{
            redisUtil.set(qrCodeID,"2",5*60);
        }
        String pcToken = "pcToken"+TokenProccessor.generateToken(tempToken);
        String qrcodeStatus = "2";
        redisUtil.set(pcToken,pcToken,5*60);
        return pcToken +"|" +qrcodeStatus;
    }

    /**
     *  pc端查询二维码状态。
     *  服务端二维码状态可放入redis中。
     * @param qrCodeID
     * @return
     */
    public String queryQrCodeSatus(String qrCodeID){
        if(queryKeyNotExsted(qrCodeID)){
            qrcodeStatus = "-1";
        }
        qrcodeStatus = (String)redisUtil.get(qrCodeID);
        return qrcodeStatus;
    }

    /**
     *  查询redis中的某个key是否不存在。不存在返回TRUE
     *
     * @param key
     * @return Boolean
     */
    private Boolean queryKeyNotExsted(String key){
        if(StringUtils.empty((String)redisUtil.get(key))){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * redis 中根据临时token查询 二维码
     * @param tempToken
     * @return
     */
    private String queryQrCodeID(String tempToken){
        String queryqrCodeID ;
        if(queryKeyNotExsted(tempToken)){
            queryqrCodeID = "";
        }else{
             queryqrCodeID = (String)redisUtil.get(tempToken);
            int index = queryqrCodeID.indexOf("|");
            queryqrCodeID = queryqrCodeID.substring(index+1);
        }
        return queryqrCodeID;
    }

    @Test
    // see RedisType
    public void test() {
        //QrcodeLogin qrl = new QrcodeLogin(); //测试的时候不能new
        //1. PC生成二维码
        String deviceID = "deviceID392093299037414";
        String qrCodeID = genQrCodeID(deviceID);
        System.out.println("PC端设备号"+deviceID+"生成二维码"+qrCodeID);
        //2. 手机已扫描二维码待确认,服务端生成临时token
        String mobileToken= "mobileToken23223323222";
        String tempToken =  genTempToken(mobileToken,qrCodeID);
        System.out.println(mobileToken+"手机已扫描二维码待确认,生成临时token"+tempToken);
        //3. 已确认，根据生成临时token的，点击登录
        String resConfirm = confirmLoginAndGenPcToken(tempToken);
        System.out.println("点击确认登录完成，返回正式token和状态"+resConfirm);
//        E:\majinhu工具软件copy\redise连接工具\Redis-x64-3.2.100

        //LOG
        //PC端设备号deviceID392093299037414生成二维码9094349977EB994E8927B33C17F160E2
        //mobileToken23223323222手机已扫描二维码待确认,生成临时token9B8B0F590D57338648E75B17EF42C46B
        //点击确认登录完成，返回正式token和状态0926656B615FD681465532C91F4E94E5|2
    }
}
