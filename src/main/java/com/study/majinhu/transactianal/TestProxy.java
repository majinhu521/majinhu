package com.study.majinhu.transactianal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName TestProxy
 * @Description
 * * 另外一种办法是把带有注解的方法移到另外一个类中，
 *  * 其他类调用时候就会使用spring动态代理增强。
 *  * 比如我们把testAnnotation类移到TestProxy类中：
 * @Author majinhu
 * @Date 2020/3/5 16:02
 * @Version 1.0
 **/
@Component
public class TestProxy {

//    @Autowired
//    private OperationLogMapper logMapper;

    @Async
    @Transactional
    public void testAsyncAndTransactionalAnnotation() throws InterruptedException{
        System.out.println("异步线程ID:" + Thread.currentThread().getId());
        OperationLog record = new OperationLog("2", "测试用");
//        logMapper.insert(record);
        System.out.println("插入记录");
        throw new RuntimeException("故意抛出一个异常");
    }

}