package com.study.majinhu.Concurrent.Binfatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * @ClassName UserController
 * @Description 测试并发调用-异步返回，主线程和子线程分别处理。
 * @Author majinhu
 * @Date 2019/6/19 15:11
 * @Version 1.0
 **/
@Controller
@RequestMapping("/main")
public class YibuUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SexCompareService sexCompareService;
    /**
     * @Author majinhu
     * @Description    Callable 子线程异步执行接口
     *             http://localhost:8088/main/user1
     * @Date 15:16 2019/6/19
     * @Param [userId]
     * @return java.lang.String
     **/
    @RequestMapping("/user1")
    @ResponseBody
    public Callable<String> getUserInfo(String userId){

        System.out.println("主线程开始处理...."+Thread.currentThread()+System.currentTimeMillis());
        //spring开辟的子线程
        Callable<String> callable = new Callable<String>(){
            @Override
            public String call() throws Exception {
                String  result= sexCompareService.getUserInfo(userId);
                return result;
            }
        };
        System.out.println("主线程结束处理...."+Thread.currentThread()+System.currentTimeMillis());
        return callable;
    }
}
