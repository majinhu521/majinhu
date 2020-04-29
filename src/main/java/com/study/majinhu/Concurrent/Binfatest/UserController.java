package com.study.majinhu.Concurrent.Binfatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName UserController
 * @Description 测试并发调用
 * @Author majinhu
 * @Date 2019/6/19 15:11
 * @Version 1.0
 **/
@Controller
@RequestMapping("/main")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SexCompareService sexCompareService;
    /**
     * @Author majinhu
     * @Description    串行和futuretask多线程并行的方法比较
     *             http://localhost:8088/main/user
     * @Date 15:16 2019/6/19
     * @Param [userId]
     * @return java.lang.String
     **/
    @RequestMapping("/user")
    @ResponseBody
    public String getUserInfo(String userId){
        //串行执行
        //return userService.getUserInfo(userId);
        //并发执行
        return sexCompareService.getUserInfo(userId);
    }
}
