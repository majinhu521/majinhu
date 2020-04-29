package com.study.majinhu.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @ClassName TimeConsumAspect
 * @Description 时间统计aop
 *
切点函数execution()的使用
 @Around("execution(* *(..))")  : execution()是一个切点函数,* * (..)是该函数的参数，其格式为:
 <访问权限>? 返回值类型  包名+类名+方法名(参数类型) <throws 异常类型声明>
 @Around("execution(* * (..))") //all
 @Around("execution(public * * (..))")   //绑定方法的访问权限
 @Around("execution(public * * (..) throws RuntimeException)")   //绑定异常类型声明
 @Around("execution(public * * (..) throws RuntimeException+)")   //+代表当前类及其子类
 @Around("execution(int * (..))")   //绑定方法的返回值
 @Around("execution(Object+ * (..))")   //绑定方法的返回值
 @Around("execution(void save* (..))")   //绑定方法名,以save开头的方法
 @Around("execution(void *m* (..))")   //包含m的方法
 @Around("execution(void com.dufy.spring.service.*.* (..))")   //绑定包名+类名+方法名
 @Around("execution(void com.dufy.spring..*Service*.update* (..))")   //包名以com.sxt.spring开头的类名中包含Service的类中所有以update开关的方法
 @Around("execution(void *())")   //绑定方法的参数
 @Around("execution(void *(String))")   //绑定方法的参数
 @Around("execution(void *(..,String,..))")   //只要有一个String类型的参数即可
 @Around("args(int,String)")
 @Around("execution(* save*(..)) || execution(* update*(..))")    //切点运算   (||,or,&&,and)
 @Around("execution(* save*(..)) or execution(* update*(..))")    //切点运算

 原文：https://blog.csdn.net/u010648555/article/details/50812135
开启需要把下面2个注释打开。
 * @Author majinhu
 * @Date 2019/6/21 10:36
 * @Version 1.0
 **/
//@Aspect
//@Component
public class TimeConsumAspect {
    private static Log logger = LogFactory.getLog(TimeConsumAspect.class);

    // 一分钟，即60000ms
    private static final long ONE_MINUTE = 60000;
    // service层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
    public static final String POINT = "execution (* com.study.majinhu.Concurrent.Binfatest.*.*(..))";

    @Around(POINT)
    public Object timeAround(ProceedingJoinPoint joinPoint){
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();

        try {
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            logger.error("统计某方法执行耗时环绕通知出错", e);
        }

        // 获取执行的方法名
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        // 打印耗时的信息
        this.printExecTime(methodName, startTime, endTime);

        return obj;
    }

    /**
     * 打印方法执行耗时的信息，如果超过了一定的时间，才打印
     * @param methodName
     * @param startTime
     * @param endTime
     */
    private void printExecTime(String methodName, long startTime, long endTime) {
        long diffTime = endTime - startTime;
        if (diffTime > ONE_MINUTE) {
            System.out.println("-----" + methodName + " 方法执行耗时超过1分钟：" + diffTime + " ms");
            logger.warn("-----" + methodName + " 方法执行耗时超过1分钟：" + diffTime + " ms");
        }else{
            System.out.println("-----" + methodName + " 方法执行耗时小于1分钟：" + diffTime + " ms");
            logger.info("-----" + methodName + " 方法执行耗时小于1分钟：" + diffTime + " ms");
        }
    }
}
