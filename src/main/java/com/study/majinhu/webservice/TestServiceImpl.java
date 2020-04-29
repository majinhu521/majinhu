package com.study.majinhu.webservice;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

/**
 * @Author majinhu
 * @Description webservice服务端实现类
 * @Date 16:25 2019/5/24
 * @Param
 * @return
 **/
@WebService(serviceName = "TradeResultNotifyService", // 与接口中指定的name一致
targetNamespace = "http://webservice.controller.SH2QD.outapi_api.metro.bestone.com", // 与接口中的命名空间一致,一般是接口的包名倒
endpointInterface = "com.study.majinhu.webservice.TestService"// 接口地址
)
@Component
public class TestServiceImpl implements TestService {

    @Override
    public String sendMessage(String username,String pwd) {
        
        return "hello "+username+"pwd"+pwd;
    }

}
