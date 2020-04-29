package com.study.majinhu.Concurrent.Binfatest;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description 用户服务，调用远程服务；串行调用远程服务。
 * @Author majinhu
 * @Date 2019/6/19 15:01
 * @Version 1.0
 **/
@Service
public class UserService {
    @Autowired
    RemoteService remoteService;
    public String getUserInfo(String userId){

        long start = System.currentTimeMillis();

        String V1 = remoteService.getUserInfo(userId);
        JSONObject userInfo = JSONObject.parseObject(V1);

        String V2 = remoteService.getUserMoney(userId);
        JSONObject userMoney = JSONObject.parseObject(V2);

        JSONObject result = new JSONObject();
        result.putAll(userInfo);
        result.putAll(userMoney);

        System.out.println( "执行总时间为"+(System.currentTimeMillis()-start));
        System.out.println( "执行结果："+result.toString());
        return result.toString();
    }
}
