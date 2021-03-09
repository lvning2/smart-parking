package com.design.smartparking.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(public * com.design.smartparking.controller.*.*(..))")
    public void webLog(){}

    @Around(value = "webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[] args = pjp.getArgs();

        StringBuilder sb=new StringBuilder();
        sb.append("URL: ")
                .append(request.getRequestURL().toString());
        sb.append(", http method: ")
                .append(request.getMethod());
        sb.append(", ip: ")
                .append(request.getRemoteAddr());
        sb.append(", class method: ")
                .append(pjp.getSignature().getDeclaringTypeName())
                .append(".")
                .append(pjp.getSignature().getName());
        sb.append(", args: ")
                .append(Arrays.toString(args));
        logger.info(sb.toString());

        Object proceed = pjp.proceed(args);
        return proceed;
    }



}
