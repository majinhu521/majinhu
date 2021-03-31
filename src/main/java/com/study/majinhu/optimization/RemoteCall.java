package com.study.majinhu.optimization;

import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName remoteCall
 * @Description
 * @Author majinhu
 * @Date 2021/3/31 16:26
 * @Version 1.0
 **/
public class RemoteCall {
    private static Logger log = LoggerFactory.getLogger(RemoteCall.class);

    public static String remoteCall(AbstractAPI api) throws IOException {
        // 从类上获得BankAPI注解，然后拿到其URL属性，后续进行远程调用
        BankAPI bankAPI = api.getClass().getAnnotation(BankAPI.class);
        bankAPI.url();
        StringBuilder stringBuilder = new StringBuilder();
        // 使用stream快速实现获取类中所有带BankAPIField注解的字段，并把字段按order属性排序，然后设置私有字段反射可访问。
        Arrays.stream(api.getClass().getDeclaredFields()) //获得所有字段
                //查找标记了注解的字段
                .filter(field -> field.isAnnotationPresent(BankAPIField.class))
                // 根据注解中的order对字段排序
                .sorted(Comparator.comparingInt(a -> a.getAnnotation(BankAPIField.class).order()))
                .peek(field -> field.setAccessible(true)) //设置可以访问私有字段
                .forEach(field -> {
                    // 实现了反射获取注解的值，然后根据BankAPIField拿到的参数类型，按照三种标准进行格式化，将所有参数的格式化逻辑集中在了这一处
                    // 获得注解
                    BankAPIField bankAPIField = field.getAnnotation(BankAPIField.class);
                    Object value = "";
                    try {
                        // 反射获取字段值
                        value = field.get(api);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    // 根据字段类型以正确的填充方式格式化字符串
                    switch (bankAPIField.type()) {
                        case "S": {
                            stringBuilder.append(String.format("%-" + bankAPIField.length() + "s", value.toString()).replace(' ', '_'));
                            break;
                        }
                        case "N": {
                            stringBuilder.append(String.format("%" + bankAPIField.length() + "s", value.toString()).replace(' ', '0'));
                            break;
                        }
                        case "M": {
                            if (!(value instanceof BigDecimal))
                                throw new RuntimeException(String.format("{} 的 {} 必须是BigDecimal", api, field));
                            stringBuilder.append(String.format("%0" + bankAPIField.length() + "d", ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
                            break;
                        }
                        default:
                            break;
                    }
                });
        // 实现参数加签和请求调用
        // 签名逻辑stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
        String param = stringBuilder.toString();
        long begin = System.currentTimeMillis();
        //发请求
       String result ="POST URL OK";
//        = HttpClient.Post("http://localhost:45678/reflection" + bankAPI.url())
//                .bodyString(param, ContentType.APPLICATION_JSON)
//                .execute().returnContent().asString();
        log.info("调用银行API {} url:{} 参数:{} 耗时:{}ms", bankAPI.desc(), bankAPI.url(), param, System.currentTimeMillis() - begin);
        return result;
    }
}
