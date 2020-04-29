package com.study.majinhu.controllerParam;

import com.study.majinhu.httpClient.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName ParamController
 * @Description Controller接收参数的几种方式
 * @Author majinhu
 * @Date 2019/6/18 16:24
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/paramSend")
public class ParamController {
    /**
     * @Author majinhu
     * @Description
     * 第一类：请求路径参数
     * 1.@PathVariable 获取路径参数。即url/{id}这种形式。
     * 2、@RequestParam
     * 获取查询参数。即url?name=这种形式
     *           http://localhost:8088/paramSend/demo/123?name=suki_rong
     * @Date 16:29 2019/6/18
     * @Param [id, name]
     * @return void
     **/
    @GetMapping("/demo/{id}")
    public void demo(@PathVariable(name = "id") String id, @RequestParam(name = "name") String name) {
        System.out.println("id="+id);
        System.out.println("name="+name);
    }

    /**
     * @Author majinhu
     * @Description
     * 第二类：Body参数
     * 1. RequestBody Body参数,需要bean对象接收
     *   postmanUrl:                http://localhost:8088/paramSend/demo1
     *       header:                Content-Type:application/json
     *       body:    json / raw   {"age":"18","name":"mahaha"}
     *       type:    post
     * @Date 16:31 2019/6/18
     * @Param [person]
     * @return void
     **/
    @PostMapping(path = "/demo1")
    public void demo1(@RequestBody Person person) {
        System.out.println(person.toString());//com.study.majinhu.controllerParam.Person@50846ca6
        System.out.println(JsonUtil.obj2Json(person));//{"age":"18","name":"mahaha"}
    }

    /**
     * @Author majinhu
     * @Description 第二类：Body参数
     * RequestBody Body参数,需要bean对象接收
     * 输入情况：json对象 {"age":"18","name":"mahaha"}
     *   postmanUrl:                http://localhost:8088/paramSend/demo2
     *       header:                Content-Type:application/json
     *       body:    json / raw   {"age":"18","name":"mahaha"}
     *       type:    post
     * @Date 16:31 2019/6/18
     * @Param [person]
     * @return void
     **/
    @PostMapping(path = "/demo2")
    public void demo2(@RequestBody Map<String, String> person) {
        System.out.println(person.get("name")); //mahaha
        System.out.println(JsonUtil.map2JsonWriteMapNullValue(person));//{"age":"18","name":"mahaha"}
    }
   /**
    * @Author majinhu
    * @Description 第二类：Body参数
    *   无注解
    *
    *   postmanUrl:                http://localhost:8088/paramSend/demo3
    *       header:                Content-Type:application/json
    *       body:    Content-Type: multipart/form-data;
    *       name:majh
    *       age:18
    *       type:    post
    * @Date 16:37 2019/6/18
    * @Param [person]
    * @return void
    **/
    @PostMapping(path = "/demo3")
    public void demo3(Person person) {
        System.out.println(person.toString());//com.study.majinhu.controllerParam.Person@1c2ed5e2
        System.out.println(JsonUtil.obj2Json(person));//{"age":"18","name":"majh"}
    }

    /**
     * @Author majinhu
     * @Description 第三类：请求头参数以及Cookie
     * @Date 16:41 2019/6/18
     * @Param
     * @return
     **/
    @GetMapping("/demo4")
    public void demo4(@RequestHeader(name = "myHeader") String myHeader,
                      @CookieValue(name = "myCookie") String myCookie) {
        System.out.println("myHeader=" + myHeader);
        System.out.println("myCookie=" + myCookie);
    }
    /**
     * @Author majinhu
     * @Description 第三类：请求头参数以及Cookie
     * @Date 16:41 2019/6/18
     * @Param
     * @return
     **/
    @GetMapping("/demo5")
    public void demo5(HttpServletRequest request) {
        System.out.println(request.getHeader("myHeader"));
        for (Cookie cookie : request.getCookies()) {
            if ("myCookie".equals(cookie.getName())) {
                System.out.println(cookie.getValue());
            }
        }
    }

    /**
     * @Author majinhu
     * @Description HttpServletRequest 请求中获取参数
     * @Date 17:18 2019/6/18
     * @Param [request]  Content-Type:application/x-www-form-urlencoded
     *
     *   postmanUrl:                http://localhost:8088/paramSend/demo6
     *       header:                 Content-Type:application/x-www-form-urlencoded
     *       body:    name:majh
     *
     *       type:    post
     * @return void
     **/
    @PostMapping("/demo6")
//    @RequestMapping(value = "/demo6")
//    @ResponseBody
    public void demo6(HttpServletRequest request) {
        System.out.println(request.getParameter("name"));

    }

}
