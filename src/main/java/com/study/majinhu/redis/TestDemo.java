package com.study.majinhu.redis;

//import com.bestone.pcs.pcs_api.utils.http.HttpClientUtil;
//import com.bestone.pcs.pcs_api.utils.json.JsonMapper;
//import com.google.common.collect.Maps;
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

    static CountDownLatch c = new CountDownLatch(1);//初始化计数器个数为3
    static int orderno = 82;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dorun(orderno + finalI + 1);
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


    public static void dorun(int orderno) {
        Map param = Maps.newHashMap();
        param.put("ownerId", "445011");
        param.put("ticketMoney", "200");
        param.put("orderNo", String.valueOf(orderno));
        param.put("orderDate", "2019-08-10 18:53:01");
        param.put("action", "2");
        param.put("metroCard", "222222");
        param.put("type", "2");
        param.put("currentMode", "2");
        String response = HttpClientUtil.doPost(URL + METHOD1, param);

        //json转换成map
//        Map result = new JsonMapper().fromJson(response, Map.class);
        System.out.println(response);
    }
}
