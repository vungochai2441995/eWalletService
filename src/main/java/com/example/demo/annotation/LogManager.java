package com.example.demo.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogManager {
    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void auditLog() {}
}