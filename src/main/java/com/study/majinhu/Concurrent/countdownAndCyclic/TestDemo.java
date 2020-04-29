package com.study.majinhu.Concurrent.countdownAndCyclic;

//import com.bestone.pcs.pcs_api.utils.http.HttpClientUtil;
//import com.bestone.pcs.pcs_api.utils.json.JsonMapper;
import com.google.common.collect.Maps;
import com.study.majinhu.httpClient.HttpClientUtil;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName TestDemo
 * @Description
 * @Author majinhu
 * @Date 2019/8/8 14:18
 * @Version 1.0
 **/
public class TestDemo {
    Logger logger = Logger.getLogger(TestDemo.class);

    private static final String URL = "http://localhost:8871";
    //红包扣减
    private static final String METHOD1 = "/receive/substractRedPacket";

    private static final String METHOD2 = "/receive/receiveOrderResult";
    static CountDownLatch c = new CountDownLatch(3);//初始化计数器个数为3
    static int orderno = 120;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //addRedpacketRun(orderno + finalI + 1);
                    //subRedpacketRun(orderno + finalI + 1);
                    returnRedpacketRun(orderno + finalI + 1);
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            c.countDown();
        }
    }
    //增加红包
    public static void addRedpacketRun(int orderno) {
        Map param = Maps.newHashMap();
        param.put("money", "300");
        param.put("phone", "17561911428");
        param.put("redpacketId", String.valueOf(orderno));
        param.put("categoryCode", "123");
        String response = HttpClientUtil.doPost("http://localhost:8072/topPicks/receiveRedPacket", param);
       // Map result = new JsonMapper().fromJson(response, Map.class);
        //System.out.println(result);
        //subRedpacketRun(orderno);
    }
    //扣减红包
    public static void subRedpacketRun(int orderno) {
        Map param = Maps.newHashMap();
        param.put("ownerId", "142910");
        param.put("ticketMoney", "200");
        param.put("orderNo", String.valueOf(orderno));
        param.put("orderDate", "2019-08-10 18:53:01");
        param.put("action", "2");
        param.put("metroCard", "222222");
        param.put("type", "2");
        param.put("currentMode", "2");
        String response = HttpClientUtil.doPost(URL + METHOD1, param);
        //Map result = new JsonMapper().fromJson(response, Map.class);
        //System.out.println(result);
    }

    //返回红包
    public static void returnRedpacketRun(int orderno) {
        Map param = Maps.newHashMap();
        param.put("thirdUserId", "142910");
        param.put("orderNo", String.valueOf(orderno));
        param.put("result", "01");

        String response = HttpClientUtil.doPost(URL + METHOD2, param);
       // Map result = new JsonMapper().fromJson(response, Map.class);
       // System.out.println(result);
    }
}
