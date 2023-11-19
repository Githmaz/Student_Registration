package edu.icet.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodStatAspect {
    @Around("@annotation(edu.icet.annotation.MethodStat)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
       long start = System.currentTimeMillis();
       Object porceed = joinPoint.proceed();
       log.info("Excution time {}ms",System.currentTimeMillis()-start);
       return porceed;
    }
}
