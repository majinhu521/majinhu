package com.study.majinhu.aop;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName AopParamVerify
 * @Description Spring AOP获取拦截方法的参数名称跟参数值
 * @Author majinhu
 * @Date 2019/6/21 11:39
 * @Version 1.0
 **/
@Aspect
public class AopParamVerify {
    private static Logger log = LoggerFactory.getLogger(AopParamVerify.class);

    @Around(value = "execution(* com.study.majinhu.aop..*.*(..))")
    public Object invoke(ProceedingJoinPoint joinPoint) {

        /**
         * 时间戳校验,超过一分钟,拦截
         */
        //返回结果封装类
        HzlqswReqResult rst = new HzlqswReqResult();
        //1.这里获取到所有的参数值的数组
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //2.最关键的一步:通过这获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        try {
            //3.通过你需要获取的参数名称的下标获取到对应的值
            int timeStampIndex = ArrayUtils.indexOf(parameterNames, "timeStamp");
            if (timeStampIndex != -1) {
                long timeStamp = (Long) args[timeStampIndex];
                if (System.currentTimeMillis() - timeStamp > 60000) {
                    rst.setResultCode(rst.resultCode_hashCode_overdue);
                    rst.setReturnMsg("时间戳过期");
                    rst.setReturnObject("时间戳过期");
                    return rst;
                }
            }
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("时间戳校验异常");
            throwable.printStackTrace();
            rst.setResultCode(rst.resultCode_error);
            rst.setReturnMsg("AOP校验异常");
            rst.setReturnObject("AOP校验异常");
            return rst;
        }
    }

    class  HzlqswReqResult{
        private String resultCode;
        private String returnMsg;
        private String returnObject;
        String resultCode_hashCode_overdue ="过期";
        String resultCode_error="错误";

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getReturnMsg() {
            return returnMsg;
        }

        public void setReturnMsg(String returnMsg) {
            this.returnMsg = returnMsg;
        }

        public String getReturnObject() {
            return returnObject;
        }

        public void setReturnObject(String returnObject) {
            this.returnObject = returnObject;
        }
    }
}