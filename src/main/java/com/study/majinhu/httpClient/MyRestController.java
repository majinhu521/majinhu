package com.study.majinhu.httpClient;

import com.study.majinhu.controllerParam.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;

/**
 * @ClassName MyRestController
 * @Description https://blog.csdn.net/qq_35981283/article/details/82056285
 * https://www.jianshu.com/p/0fd5f3f64137
 * https://blog.csdn.net/u012702547/article/details/77917939/
 * @Author majinhu
 * @Date 2019/7/10 9:14
 * @Version 1.0
 **/
@RestController
public class MyRestController {
    private static final Logger logger = LoggerFactory.getLogger(MyRestController.class);

    @Autowired
    private RestTemplate restTemplate;
    private static final String URL = "http://localhost:8088/server";
    //ParamController
    private static final String POST_URL = "http://localhost:8088/server2";

    @GetMapping("/default")
    public void defaultRestClient(){
        String result = restTemplate.getForObject(URL,String.class);
        logger.info("result = {}",result);
    }


    @GetMapping("/default2")
    public void defaultRestClient2(){


        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity(POST_URL, null, Object.class);
        logger.info("result = {}",objectResponseEntity.getBody());

    }

}
