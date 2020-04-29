package com.study.majinhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName testController
 * @Description 最小的启动的单元。
 *              http://localhost:8088/testController/method
 * @Author majinhu
 * @Date 2019/5/30 11:43
 * @Version 1.0
 **/
@Controller
@RequestMapping("/testController")
public class HelloController {

    @ResponseBody
    @RequestMapping("/method")
    public String getrequest(){
        return "hello";
    }

}
