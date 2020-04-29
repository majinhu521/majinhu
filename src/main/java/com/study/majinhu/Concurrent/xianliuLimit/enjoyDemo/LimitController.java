package com.study.majinhu.Concurrent.xianliuLimit.enjoyDemo;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName LimitController
 * @Description 享学20190801demo
 *    http://localhost:8088/limit/killProduct/?name=king
 * @Author majinhu
 * @Date 2019/8/2 8:42
 * @Version 1.0
 **/
@Controller
@RequestMapping("/limit")
public class LimitController {
    //初始化令牌桶
    RateLimiter rateLimiter = RateLimiter.create(5);

    @RequestMapping("/killProduct")
    @ResponseBody
    public String KillProduct(@RequestParam(required = true)String name)throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
        int i = 1;
        if(name.equals("king")){ i = 5; };
        if(rateLimiter.tryAcquire(i)){ //获取到了令牌
            System.out.println("处理时间"+formatter.format(new Date())+"业务处理"+name);
            return "恭喜你，秒杀成功";
        }else{
            System.out.println("被限流了"+formatter.format(new Date())+name);
            return "对不起，被限流了";
        }
    }
}
