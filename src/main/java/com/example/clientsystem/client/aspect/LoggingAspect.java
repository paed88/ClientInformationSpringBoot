package com.example.clientsystem.client.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    private static final org.slf4j.Logger aopLogger =
            org.slf4j.LoggerFactory.getLogger("AOP_BUSINESS_LOG");

    @Before("execution(* com.example.clientsystem.client.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        aopLogger.info(">>> [METHOD START] : {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.example.clientsystem.client.service.*.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {

        String methodName = joinPoint.getSignature().getName();

        if (result instanceof List) {
            aopLogger.info("<<< [METHOD END] : {} | Items found: {}", methodName, ((List<?>) result).size());
        } else {
            aopLogger.info("<<< [METHOD END] : {} | Data: {}", methodName, result);
        }
    }

}
