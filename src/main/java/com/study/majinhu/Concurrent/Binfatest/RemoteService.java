package com.study.majinhu.Concurrent.Binfatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RemoteService
 * @Description 远程调用服务
 * @Author majinhu
 * @Date 2019/6/19 14:44
 * @Version 1.0
 **/
@Service
public class RemoteService {
//    @Autowired
//    RestTemplate restTemplate;

    public String getUserInfo(String userId){
        long startmills = System.currentTimeMillis();
        //返回jsontostring的结果
        String result = "{\"force\":\"false\",\"token\":\"E9707C1F091A944F1DFA21277F3F1D40\"}";
        //String result = restTemplate.getForEntity("http://127.0.0.1:8088/user/getUserInfo?user="+userId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取用户基本信息"+(System.currentTimeMillis()-startmills));
        return result;
    }

    public String getUserMoney(String userId){
        long startmills = System.currentTimeMillis();
        String result = "{\"deviceCoding\":\"cb464cc19d68ee7b\",\"platform\":\"iOS\"}";
        //String result = restTemplate.getForEntity("http://127.0.0.1:8088/user/getUserInfo?user="+userId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取用户金额"+(System.currentTimeMillis()-startmills));
        return result;
    }
}
