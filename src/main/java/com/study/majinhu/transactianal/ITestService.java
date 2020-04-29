package com.study.majinhu.transactianal;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ITestService
 * @Description
 * @Author majinhu
 * @Date 2020/3/5 15:55
 * @Version 1.0
 **/
public interface ITestService {

    public default void testAnnotation() throws InterruptedException{

    }

    public default void testAsyncAndTransactionalAnnotation() throws InterruptedException{

    }
}
