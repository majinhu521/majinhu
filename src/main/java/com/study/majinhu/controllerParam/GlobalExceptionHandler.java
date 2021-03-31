package com.study.majinhu.controllerParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.study.majinhu.controllerParam.ResponseData;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobalExceptionHandler
 * @Description http://c.biancheng.net/view/5316.html
 * ResponseData 是我们返回格式的实体类，其发生错误时也会被捕获到，然后封装好返回格式并返回给调用方。
 * 最后关键的一步是，在 Spring Boot 的配置文件中加上如下代码所示配置。
 * # 出现错误时, 直接抛出异常
 * spring.mvc.throw-exception-if-no-handler-found=true
 * # 不要为我们工程中的资源文件建立映射
 * spring.resources.add-mappings=false
 *
 * 然后当我们调用一个不存在的接口时，返回的错误信息就是我们自定义的那种格式了：
 * {
 *     "status": false, "code": 404,
 *     "message": "No handler found for GET /rest11/auth", "data": null
 * }
 * @Author majinhu
 * @Date 2021/3/18 15:23
 * @Version 1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("", e);
        ResponseData r = new ResponseData();
        r.setMessage(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            r.setCode(404);
        } else {
            r.setCode(500);
        }
        r.setData(null);
        r.setStatus(false);
        return r;
    }
}
