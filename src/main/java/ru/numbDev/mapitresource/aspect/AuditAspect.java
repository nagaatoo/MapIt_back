package ru.numbDev.mapitresource.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditAspect {

//    @Around("execution(* ru.numbDev.mapitresource.controller.UserController.*(..))")
//    public Object audit(ProceedingJoinPoint jp) throws Throwable {
//        return jp.proceed();
//    }
}
