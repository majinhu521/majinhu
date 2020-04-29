package com.study.majinhu.Concurrent.Binfatest;

import com.study.majinhu.MajinhuApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName TaskTestAsync
 * @Description  测试异步执行 Async 注解
 *   1.异步执行方法上面需要添加@Async
 *   2.MajinhuApplication 需要注解 @EnableAsync
 *   3.不等待执行结果。
 *   java.lang.InterruptedException: sleep interrupted异常，原因是因为单元测试启动的主线程很快就结束了，而子线程确sleep5秒，
 *   使得主线程强行打断子线程的sleep,因此抛出异常，解决办法是可以在单元测试的最后加上sleep(10*1000),目的是不让主线程在子线程前结束。
 * @Author majinhu
 * @Date 2019/6/21 13:54
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MajinhuApplication.class)
public class TaskTestAsync {
    @Autowired
    private TaskTest tasktest;
    @Test
    public void test() throws InterruptedException {
        tasktest.doTaskOne();
        tasktest.doTaskTwo();
        tasktest.doTaskThree();
        Thread.sleep(10*1000);
    }
}
