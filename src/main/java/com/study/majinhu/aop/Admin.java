package com.study.majinhu.aop;

import java.lang.annotation.*;

/**
 * @ClassName Admin
 * @Description
 * @Author majinhu
 * @Date 2019/5/21 9:11
 * @Version 1.0
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Admin {
    String value() default "";
}
