package com.xishanqu.kafka.common.aop;

import com.xishanqu.kafka.common.annotation.ApiCount;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 中文类名: 统计接口流量
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/12/20
 */
@Aspect
@Component
@Slf4j
public class ApiCountAdvice {

    /** 定义切点，在注解的位置切入 */
    @Pointcut("@annotation(com.xishanqu.kafka.common.annotation.ApiCount)")
    public void apiCountPointCut(){
    }


    /** 切面，配置通知 */
    @AfterReturning("apiCountPointCut()")
    public void apiCount(JoinPoint joinPoint){
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //获取切入点所在的方法
        Method method = signature.getMethod();

        ApiCount apiCount = method.getAnnotation(ApiCount.class);
        if (apiCount != null){

            String shopId = apiCount.shopId();

            String userId = apiCount.userId();

            log.info("value====sss===========,{}",shopId);
            log.info("userId===sss============,{}",userId);
        }




    }


}
