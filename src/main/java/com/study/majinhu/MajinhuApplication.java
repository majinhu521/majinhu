package com.study.majinhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAsync
@RestController
@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class MajinhuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MajinhuApplication.class, args);
    }


    @GetMapping("/server")
    public String restServer(){
        System.out.println("This is restget server");
        return "success";
    }

    @PostMapping("/server2")
    public String restServer2(){
        System.out.println("This is restpost server");
        return "success";
    }

}
