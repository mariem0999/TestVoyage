package tn.esprit.spring.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TrasabiliteClass {

    @Before("execution(* tn.esprit.spring.Services.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getDeclaringTypeName();
        log.info("In method " + name + "  ");
    }

    @AfterReturning("execution(* tn.esprit.spring.Services.*.*(..))")
    public void logMethodEnd(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getDeclaringTypeName();
        log.info("Out of method " + name + "  ");
    }

}
