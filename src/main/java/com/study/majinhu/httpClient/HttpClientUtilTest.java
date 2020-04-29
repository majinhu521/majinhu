package com.study.majinhu.httpClient;


/**
 * @ClassName MainTest
 * @Description 测试客户端调用
 * @Author HZY
 * @Date 2019/6/10 16:47
 * @Version 1.0
 **/
public class HttpClientUtilTest {

    public static void main(String[] args){
        //测试post 不带参数
        System.out.println(testPost("testController/method"));

        //测试post 带参数
        System.out.println(testGet("testController/method"));

    }
    //测试post
    public static String testPost(String method){

        String responseMsg=HttpClientUtil.doPost("http://localhost:8088/"+method);
        return responseMsg;
    }

    //测试GET 不带参数
    public static String testGet(String method){
        String responseMsg=HttpClientUtil.doGet("http://localhost:8088/"+method);
        return responseMsg;
    }


}
