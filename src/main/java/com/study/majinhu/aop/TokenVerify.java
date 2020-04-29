package com.study.majinhu.aop;

import java.lang.annotation.*;

/**
 * @ClassName TokenVerify
 * @Description 登录验证注解 @TokenVerify
 * 作用于方法，类
 * @Author majinhu
 * @Date 2019/6/26 8:38
 * @Version 1.0
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenVerify {
    String key() default "";
    String cacheName() default "";
    boolean needLog() default false;
}
