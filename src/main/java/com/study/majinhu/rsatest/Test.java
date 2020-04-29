package com.study.majinhu.rsatest;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程：
 * （1）合作方收到新网报文后进行验签(使用新网公钥)-->解密(使用合作方私钥)操作；【dencryptAndVerify()】
 * （2）合作方将返回报文进行加密(使用新网公钥)-->加签(使用合作方私钥)后发送给新网【encryptAndSign()】。
 */
public class Test {

    //合作方公钥
    private String publicKeyA = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCB6zzThZwogmr/YLXNhvTlqXbpcuy4AWgBlxUZiSZwn0nLAmly63Nod1jAHUsEsgFvfyKXt9cXxV88XDDYpcJLvNZFBF71AKRKpu+ozApHmlKUrqCnR5GvPdU20A3dPNIQhAlWeQMdafnMmMkuzPF5shY8Rm8SL/atkJfGDJebQQIDAQAB";

    //合作方私钥
    private String privateKeyA = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIHrPNOFnCiCav9gtc2G9OWpduly7LgBaAGXFRmJJnCfScsCaXLrc2h3WMAdSwSyAW9/Ipe31xfFXzxcMNilwku81kUEXvUApEqm76jMCkeaUpSuoKdHka891TbQDd080hCECVZ5Ax1p+cyYyS7M8XmyFjxGbxIv9q2Ql8YMl5tBAgMBAAECgYApUhQqglhEWhaF2tB36fCCCwATB8ZaT7gwqUjAsmpTb5Ycx0uajWBLXYoQsEBn4eWq/JiYAJScrt23aOrwPOgqkJx5A271PUb78v0m+28L9g563/wk+qCKMpo9QVx56B0IWLaAj3Ko0x8aBDYxNWcKPISIQnkW/s3o6dS0SRLGsQJBANqpj+bvdD82yH/JenR9VCDuGNzBSdy26+qibv9VKnKxQ1XYOD7T4qYUuBuC9lGnHLQ7fTA/rW3Qm1bXjq2X9pcCQQCYGmmPHY0VYf9m845gtl9X26dqB8jS4vUAtYJBsSRQgMopifkQPV/ur2tnNrptVd0FiIw0Fjj82BxAKqXvDM/nAkEAzbGITwOeZQDR53POn9pt03RnKvMg2mGw183pgIPLJI0f/JcjgeasZPNEpYyyzejYFauIfmTIbm84UZ8A9sfnDQJAM3hUmiJU2JSF2a+3UVDFaLSirUWfFoXIhInnXxVzhW5ferj+uBkcumdP/+hMI613kmTHlOSkOiNjQ+9qoT8DpwJAJ8B0cfLp5+hAkQEg+vKfZU3JUxxBAc4TIG7lh+K/7+L5OAnEL/RJS213UNjOGZoyPo4EBfkENqr/sNG/WOegVA==";

    //新网公钥
    private String publicKeyB = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMPrLQjK2zE05km3TlaQ1BZ7DVRIkQy3PJYWBVse5lzKsAhxcP2K8Pc1E3XDBo2gAioKciZmFy58gBoiQuB2FH3ZKaCvg9aJGTrSxelX1vDQU2Hz7lvCu2Ytsq8gCRCNXH9xOqZlxXtNrBn8GqZd9qE9eUJuuveUnTfzTagcMo+wIDAQAB";



    //验签&解密
    @org.junit.Test
    public void dencryptAndVerify() throws Exception {
        Map<String, String> map = new HashMap<>();
        //注：**假数据，实际操作以接收新网报文为准**
        map.put("sign", "HvOCdP8HpGSJ0i/lcSZFWVXY/OuAVzmK+oFYaEFpYAmY+OwW1yNN/4a87xJ5QkmkQ60z+CbqAhWx58zclwk3Y/K1GL1wQ6+CJQvhhzIF45RbBFOIkCNzMdHZy2XiPPxp80f8rq5RCxqH6/wd1ZJvVBRJ3zqxXaddp3/RXjMeHkM=");
        map.put("randomKey", "J3p2Y+3gMfb1QTz00LcrcqTGosoZ5eVFkhJdK1s1R4l9b1G+yQhsGWL0ykQnSSEZNNac8pUQ8jG5ts6r14B93v5SgNzufWtTMN+Uy1BGd2JKLr2OkwsGudTIRV0LGq+giqtMAtisRWggicP4krSzjNOUKMkJYpfZ0slAyTZBwn8=");
        map.put("data", "BdZx+P2HewmPAjDRkGXL3bMMSeuiJgP94FaDsGePQMKfhbc82jb6WJdOqi1y8c+zmUkgAVDvmiJC/FrHlkBQHeoqz5N7EuvoPmWCOgRET/+M9J3eMUTjJGJtiti3Xr7uZFvjKY0O0sMsnUfeMc1Cxua3VlUmxnpoX35JOmWCJe0=");

        // 实例化验签,使用新网的公钥对待验签内容进行验签
        OpenSigner openSigner = OpenSigner.getInstance("RSA", "SHA256withRSA", publicKeyB, null, Charsets.UTF_8);
        // 验签
        boolean sign = openSigner.verify(map, "sign");
        System.out.println("验签结果: " + sign);
        if (!sign) {
            throw new Exception("验签失败");
        }
        String randomKey = map.get("randomKey");
        // 实例化非对称解密,使用合作方的私钥对随机秘钥进行解密
        OpenCipher asymmetricCipher = new OpenAsymmetricCipher(randomKey, "RSA", null, privateKeyA, Charsets.UTF_8);
        // 解密,得到随机秘钥
        String password = asymmetricCipher.decrypt();
        System.out.println("随机秘钥: " + password);
        String data = map.get("data");
        // 实例化对称解密,使用随机秘钥对报文进行解密
        OpenCipher symmetricCipher = new OpenSymmetricCipher(data, password, "AES", "AES/ECB/PKCS5Padding",
                Charsets.UTF_8, true);
        String content = symmetricCipher.decrypt();
        System.out.println("解密后的报文: " + content);
    }

    //加密&加签
    @org.junit.Test
    public void encryptAndSign() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("idCard", "123123123123123123");
        map.put("phone", "12345678901");
        map.put("cpOpenId", "123123123");
        map.put("cardNo", "123123123");
        String source = JSONObject.toJSONString(map);
        System.out.println("原始报文: " + source);
        map.clear();
        // 随机秘钥
        String password = StringUtils.randomKey();
        System.out.println("随机秘钥: " + password);
        // 实例化非对称加密,使用新网的公钥对随机秘钥进行加密
        OpenCipher asymmetricCipher = new OpenAsymmetricCipher(password, "RSA", publicKeyB, null, Charsets.UTF_8);
        // 加密,得到加密后的随机秘钥
        String randomKey = asymmetricCipher.encrypt();
        // 实例化对称加密,使用随机秘钥对报文进行加密
        OpenCipher symmetricCipher = new OpenSymmetricCipher(source, password, "AES", "AES/ECB/PKCS5Padding",
                Charsets.UTF_8, true);
        // 加密,得到加密后的报文
        String data = symmetricCipher.encrypt();
        map.put("randomKey", randomKey);
        map.put("data", data);
        //使用合作方的私钥进行加签操作
        OpenSigner openSigner = OpenSigner.getInstance("RSA", "SHA256withRSA", null, privateKeyA, Charsets.UTF_8);
        // 加签,得到包含签值的报文
        map = openSigner.sign(map, "sign");

        //封装响应参数
        map.put("returnCode", "000000");
        map.put("returnMsg", "成功");
        System.out.println("加密、加签后的报文: " + JSONObject.toJSONString(map));
    }
















    //该方法用于生成公私钥对，可选择性使用也可使用自己方式生成公私钥对
    @org.junit.Test
    public void generateKey() throws Exception {
        OpenSigner signer = OpenSigner.getInstance("RSA", null);

        signer.initKey();
        String publicKey = signer.getPublic();
        String privateKey = signer.getPrivate();

        System.out.println("公钥: " + publicKey);
        System.out.println("私钥: " + privateKey);
    }

}
