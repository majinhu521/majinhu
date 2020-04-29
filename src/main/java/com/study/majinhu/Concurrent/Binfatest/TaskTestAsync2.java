package com.study.majinhu.Concurrent.Binfatest;

import com.study.majinhu.MajinhuApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Future;

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
public class TaskTestAsync2 {
    @Autowired
    private TaskTest2 tasktest2;
    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> stringFuture1 = tasktest2.doTaskOne();
        Future<String> stringFuture2 =tasktest2.doTaskTwo();
        Future<String> stringFuture3 =tasktest2.doTaskThree();
       // Thread.sleep(10*1000);  //TimeConsumAspect开启aop
        while(true){
            if(stringFuture1.isDone()&&stringFuture2.isDone()&&stringFuture3.isDone()){
                System.out.println("三个线程执行完毕退出"+stringFuture1.get()+stringFuture2.get()+stringFuture3.get());
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成"+(end-start)+"毫秒");
    }
}
