package com.winter.app.transfer;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;

@Component
@Aspect
@Slf4j
public class Card {

    @Around("execution(* com.winter.app.transfer.*.*(..))")
    public Object checkCard(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before 카드 찍기");

        log.info("joinpoint = {}", Arrays.deepToString(joinPoint.getArgs()));


        Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg instanceof QQQQ)
                .forEach(arg->((QQQQ) arg).setA(3000));

        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        Object result = joinPoint.proceed();
        log.info("time = {}", Calendar.getInstance().getTimeInMillis() - timeInMillis);

        log.info("result = {}", result);
        log.info("After 카드 찍기");

        if(result instanceof QQQQ) ((QQQQ) result).setA(1234);

        return result;
    }
}
