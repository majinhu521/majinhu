package com.study.majinhu.Concurrent.xianliuLimit;

/**
 * @ClassName ServiceLimit
 * @Description AOP实现限流 aop接口
 * @Author majinhu
 * @Date 2019/6/25 11:33
 * @Version 1.0
 **/
public  @interface ServiceLimit {
    String description()  default "";
}
