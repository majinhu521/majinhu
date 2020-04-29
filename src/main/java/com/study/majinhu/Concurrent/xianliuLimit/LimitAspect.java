package com.study.majinhu.Concurrent.xianliuLimit;



import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @ClassName LimitAspect
 * @Description
 * @Author majinhu
 * @Date 2019/6/25 11:34
 * @Version 1.0
 **/
/**
 * 限流 AOP
 * 限流采用 @ServiceLimit ；采用开源工具包guava提供的限流工具类RateLimiter进行API限流，该类基于"令牌桶算法"
 * 分布式限流可用 redis 结合 aop使用。
 */
@Component
@Scope
@Aspect
public class LimitAspect {
    //每秒只发出100个令牌，此处是单进程服务的限流，内部采用令牌捅算法实现
    private static RateLimiter rateLimiter = RateLimiter.create(100.0);

    //Service层切点  限流 =com.study.majinhu.Concurrent.xianliuLimit.ServiceLimit
    // @ServiceLimit 对应service加上对应的注释 @ServiceLimit ，连接点进行连接
    @Pointcut("@annotation(com.study.majinhu.Concurrent.xianliuLimit.ServiceLimit)")
    private void ServiceAspect() {

    }

    @Around("ServiceAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        Boolean flag = rateLimiter.tryAcquire();
        Object obj = null;
        try {
            if(flag){
                obj = joinPoint.proceed();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }
}
