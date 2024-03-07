package com.winter.app.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class ServiceLogger {

    //공통로직 Advice
    @Around("execution(* com.winter.app.*.*Service.*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {

        log.info("========== Service 실행 ==============");
        log.info("매개변수 : {}" , Arrays.deepToString(pjp.getArgs()));

        Object obj = pjp.proceed();

        log.info("리턴값 : {}" , obj.toString());
        log.info("========== Service 종료 ==============");

        return obj;
    }
}
