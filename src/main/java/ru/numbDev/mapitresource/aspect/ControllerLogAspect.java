package ru.numbDev.mapitresource.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.numbDev.mapitresource.mixin.Front;
import ru.numbDev.mapitresource.utils.ThrowUtils;

@Component
@Aspect
public class ControllerLogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* ru.numbDev.mapitresource.controller.*.*(..))")
    public Object logger(ProceedingJoinPoint jp) throws Throwable {
        var params = jp.getArgs();

        for (Object param : params) {

            if (param instanceof Front) {
                Front o = (Front) param;

                if (o.isEmpty()) {
                    logger.error("Object is empty: " + jp.getClass().getName());
                    throw ThrowUtils.throwEx("Object is not empty", 400);
                }

            }

        }

        Object res = null;
        try {
            res = jp.proceed();
        } catch (Throwable e) {
            logger.error("Error execution method");
            throw e;
        }

        logger.info("Success operation");
        return res;
    }

}
