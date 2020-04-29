package com.study.majinhu.aop;

import com.alibaba.fastjson.JSON;
//import com.bestone.bean.RespCode;
//import com.bestone.metro.customer.CustomerLogin;
//import com.bestone.metro.service.customer.CustomerService;
//import com.bestone.responseEntity.BaseVo;
//import com.bestone.utils.JsonMapper;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;
import java.util.Map;

/**
 * @ClassName TokenVerifyAspect
 * @Description  需要引入 aop 的包
 *     <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-aop</artifactId>
 *         </dependency>
 * @Author majinhu
 * @Date 2019/6/26 8:57
 * @Version 1.0
 **/
@Aspect
@Component
public class TokenVerifyAspect {
//    private Logger logger =  LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private CustomerService customerService;
//
////    @Autowired
////    private LogService logService;
// //针对类的切点使用@within ， @annotation是针对方法的。
//    @Pointcut(value = "@annotation(com.bestone.metro.service.aop.TokenVerify1)")
//    //@Pointcut(value = "@within(com.bestone.metro.metro_customer.controller.aop.TokenVerify)")
//    public void annotationPointCut() {
//    }
//
//    @Around("annotationPointCut()")
//    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        String methodName = signature.getMethod().getName();
//        System.out.println("方法名：" + methodName);
//        //通过joinPoint.getTarget()获取方法名
//        Object target = joinPoint.getTarget().getClass().getName();
//        //System.out.println("调用者:"+target);
//        //通过joinPoint.getArgs()获取Args参数
//        Object[] args = joinPoint.getArgs();//2.传参
//        System.out.println("2.传参:----"+args[0]);
//        for (Object object : args) {
//            System.out.println(object instanceof HttpServletRequest);
//        }
//
//        //  https://blog.csdn.net/zknxx/article/details/53240959
//
//        //获取RequestAttributes
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        //从获取RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//
//        Enumeration<String> enumeration = request.getParameterNames();
//        Map<String,String> parameterMap = Maps.newHashMap();
//        while (enumeration.hasMoreElements()){
//            String parameter = enumeration.nextElement();
//            parameterMap.put(parameter,request.getParameter(parameter));
//        }
//        String str = JSON.toJSONString(parameterMap);
//        if(args.length > 0) {
//            System.out.println("请求的参数信息为："+str);
//        }
//        logger.info("输入参数日志：类名:"+target.toString()+"方法名："+methodName+"输入参数："+str);
//        //通过反射获取注解的值
//        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
//        TokenVerify1 tokenVerify = (TokenVerify1) method.getAnnotation(TokenVerify1.class);
//        boolean needlog=tokenVerify.needLog();
//        String key=tokenVerify.key();
//        String key2=getKey(key,joinPoint);
//        System.out.println(needlog);
//        System.out.println(key);
//        System.out.println(key2);
//        String token = parameterMap.get("token").toString();
//        BaseVo baseVo = new BaseVo();
//        if (StringUtils.isBlank(token)) {
//            baseVo.setData(null);
//            baseVo.setRespcod(RespCode.FAILED);
//            baseVo.setRespmsg("请先登录");
//            return JsonMapper.toJsonString(baseVo);
//        }
//        //获取登录信息
//        CustomerLogin customerLogin = customerService.getCustomerInformation(token,null);
//        if (customerLogin==null){
//            baseVo.setData(null);
//            baseVo.setRespcod(RespCode.FAILED);
//            baseVo.setRespmsg("请先登录");
//            return JsonMapper.toJsonString(baseVo);
//        }
//        String phone = customerLogin.getPhoneNumber();
//        if (StringUtils.isBlank(phone)){
//            baseVo.setData(null);
//            baseVo.setRespcod(RespCode.FAILED);
//            baseVo.setRespmsg("请先登录");
//            return JsonMapper.toJsonString(baseVo);
//        }
//
//        Object proceed = null;
//        try {
//             proceed = joinPoint.proceed();
//        } catch (Exception e) {
//            if(needlog){
//                logger.info("错误日志：类名:" + target.toString() + "方法名：" + methodName + "错误信息：" + e.getMessage());
//            }
//            baseVo.setRespcod(RespCode.ERROR);
//            baseVo.setRespmsg("接口异常");
//            baseVo.setData(null);
//            return JsonMapper.toJsonString(baseVo);
//        }
//        if(needlog) {
//            logger.info("成功日志：类名:" + target.toString() + "方法名：" + methodName + "成功执行");
//        }
//        return  proceed;
//    }
//    private String getKey(String key, ProceedingJoinPoint pjp){
//        Method method =  ((MethodSignature) pjp.getSignature()).getMethod();
//        Parameter[] parameters = method.getParameters();
//        return SpelParer.getkey(key,new String[]{parameters[0].getName()},pjp.getArgs());
//    }
}
