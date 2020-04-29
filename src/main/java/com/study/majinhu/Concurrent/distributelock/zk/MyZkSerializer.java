package com.study.majinhu.Concurrent.distributelock.zk;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.commons.codec.Charsets;

/**
 * @ClassName Myserialize
 * @Description 定义自己的序列化类，使用UTF-8编码
 * ---------------------
 *     作者：xiaoliuliu2050
 *     来源：CSDN
 *     原文：https://blog.csdn.net/xiaoliuliu2050/article/details/82498266
 *     版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Author majinhu
 * @Date 2019/7/2 13:32
 * @Version 1.0
 **/
public class MyZkSerializer implements ZkSerializer {
    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return new String(bytes, Charsets.UTF_8);
    }
    @Override
    public byte[] serialize(Object obj) throws ZkMarshallingError {
        return String.valueOf(obj).getBytes(Charsets.UTF_8);
    }
}
