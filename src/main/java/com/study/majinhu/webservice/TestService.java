package com.study.majinhu.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @Author majinhu
 * @Description webservice服务端
 * @Date 16:08 2019/6/11
 * @Param
 * @return
 **/
@WebService(name = "TradeResultNotifyService", // 暴露服务名称
targetNamespace = "http://webservice.majinhu.study.com"// 命名空间,一般是接口的包名倒序
)
public interface TestService {
    
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String sendMessage(@WebParam(name = "username") String username, @WebParam(name = "pwd") String pwd);
}