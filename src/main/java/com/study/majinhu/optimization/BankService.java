package com.study.majinhu.optimization;

import com.sun.deploy.nativesandbox.comm.Request;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName BankService
 * @Description https://developer.aliyun.com/article/776399?spm=a2c6h.12873581.0.0.b6ac298aiGMsNK&groupCode=javaup
 *
 * 三种标准数据类型的处理逻辑有重复
 * 处理流程中字符串拼接、加签和发请求的逻辑，在所有方法重复
 * 实际方法的入参的参数类型和顺序，不一定和接口要求一致，容易出错
 * 代码层面针对每一个参数硬编码，无法清晰地进行核对，如果参数达到几十个、上百个，出错的概率极大。
 * @Author majinhu
 * @Date 2021/3/31 16:08
 * @Version 1.0
 **/
public class BankService {

    // 创建用户
    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        // 字符串靠左，多余的地方填充_
        stringBuilder.append(String.format("%-10s", name).replace(' ', '_'));
        stringBuilder.append(String.format("%-18s", identity).replace(' ', '_'));
        // 数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%05d", age));
        // 字符串靠左
        stringBuilder.append(String.format("%-11s", mobile).replace(' ', '_'));
        // MD5签名
        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
        return  Post("POST URL OK");
//        return Request.Post("http://localhost:45678/reflection/bank/createUser")
//                .bodyString(stringBuilder.toString(), ContentType.APPLICATION_JSON)
//                .execute().returnContent().asString();
    }

    // 支付
    public static String pay(long userId, BigDecimal amount) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        // 数字靠右
        stringBuilder.append(String.format("%020d", userId));
        // 金额向下舍入2位到分，以分为单位，作为数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%010d", amount.setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
        // MD5签名
        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
        return  Post("POST URL OK");
//                Request.Post("http://localhost:45678/reflection/bank/pay")
//                .bodyString(stringBuilder.toString(), ContentType.APPLICATION_JSON)
//                .execute().returnContent().asString();
    }

    public static String Post(String url){
        return "ok";
    }


    public static String createUser2(String name, String identity, String mobile, int age) throws IOException {
        CreateUserAPI createUserAPI = new CreateUserAPI();
        createUserAPI.setName(name);
        createUserAPI.setMobile(mobile);
        createUserAPI.setIdentity(identity);
        createUserAPI.setAge(age);
        return RemoteCall.remoteCall(createUserAPI);

    }

    public static String pay2(Long userID, BigDecimal amount) throws IOException {
        PayAPI payAPI = new PayAPI();
        payAPI.setUSERID(userID);
        payAPI.setAmount(amount);
        return RemoteCall.remoteCall(payAPI);
    }

    public static void main(String[] args){
        try {
            createUser2("zhangsan","3702","13814714111",18);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}