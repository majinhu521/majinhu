package com.study.majinhu.transactianal;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName TestService
 * @Description  Transactional 失败案例。
 * https://www.lovecto.cn/20180823/228.html
 * @Author majinhu
 * @Date 2020/3/5 15:52
 * @Version 1.0
 **/
public class TestService implements ITestService{

    @Autowired
    private OperationLogMapper logMapper;

    /**
     * 测试注解 错误示范
     * @return
     * @throws InterruptedException
     */
    @Override
    public void testAnnotation() throws InterruptedException{
        System.out.println("主线程ID:" + Thread.currentThread().getId());
        testAsyncAndTransactionalAnnotation();
    }

    //@Override  正确方式1，获取AOP代理对象去执行对应的事务注解。
    public void testAnnotation2() throws InterruptedException{
        System.out.println("主线程ID:" + Thread.currentThread().getId());
        ITestService service = (ITestService) AopContext.currentProxy();
        service.testAsyncAndTransactionalAnnotation();
    }

    @Async//加入异步注解
    @Transactional//加入事务注解
    @Override
    public void testAsyncAndTransactionalAnnotation() throws InterruptedException{
        System.out.println("异步线程ID:" + Thread.currentThread().getId());
        //数据库操作，插入一条记录，可以换成任意的数据库写操作
        OperationLog record = new OperationLog("1",  "测试用");
        logMapper.insert(record);
        throw new RuntimeException("故意抛出一个异常");
    }

}
