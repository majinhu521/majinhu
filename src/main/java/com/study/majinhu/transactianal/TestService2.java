package com.study.majinhu.transactianal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestService2
 * @Description
 * 另外一种办法是把带有注解的方法移到另外一个类中，
 * 其他类调用时候就会使用spring动态代理增强。
 * 比如我们把testAnnotation类移到TestProxy类中：
 * @Author majinhu
 * @Date 2020/3/5 16:05
 * @Version 1.0
 **/
@Service
public class TestService2 {

    @Autowired
    private TestProxy proxy;

    /**
     * 测试注解
     * @return
     * @throws InterruptedException
     */
    public void testAnnotation() throws InterruptedException{
        System.out.println("主线程ID:" + Thread.currentThread().getId());
        proxy.testAsyncAndTransactionalAnnotation();
    }
}