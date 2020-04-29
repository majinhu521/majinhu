package com.study.majinhu.aop;

import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AdminAspect
 * @Description  使用@Admin注解来实现 aop验证。
 *                 https://my.oschina.net/mengyuankan/blog/2993187
 * @Author majinhu
 * @Date 2019/5/21 9:15
 * @Version 1.0
 **/
@Aspect
@Component
public class AdminAspect {
    @Pointcut(value = "@annotation(com.study.majinhu.aop.Admin)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        System.out.println("方法名：" + methodName);
        //通过joinPoint.getTarget()获取方法名
        Object target = joinPoint.getTarget().getClass().getName();
        System.out.println("调用者+"+target);
        //通过joinPoint.getArgs()获取Args参数
        Object[] args = joinPoint.getArgs();//2.传参
        System.out.println("2.传参:----"+args[0]);
        for (Object object : args) {
            System.out.println(object instanceof HttpServletRequest);
        }

        if (!validate()) {

        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return null;
        }
    }

    private boolean validate() {
        // TODO 实现自己的鉴权功能
        //checkPermission(userno);

        return false;
    }
}