package com.example.demo.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogInterceptor {
    private Logger logger = null;

    @Before(value = "com.example.demo.annotation.LogManager.auditLog()"
            + "&& target(bean) "
            + "&& @annotation(com.example.demo.annotation.Loggable)",
            argNames = "bean")
    public void endpointBefore(JoinPoint p,Object bean) {
        if (logger == null){
            logger = LoggerFactory.getLogger(bean.getClass().getName());
        }
            Object[] signatureArgs = p.getArgs();
            ObjectMapper mapper = new ObjectMapper();
            try {

                if (signatureArgs[0] != null) {
                    logger.info("Request object: \n" + mapper.writeValueAsString(signatureArgs[0]));
                }
            } catch (JsonProcessingException e) {
                logger.info(String.format("", p.getSignature().getName(), e.getMessage()));
            }
    }

    @AfterReturning(value = "com.example.demo.annotation.LogManager.auditLog()"
            + "&& target(bean) "
            + "&& @annotation(com.example.demo.annotation.Loggable)",
            returning = "result")
    public void resultLog(JoinPoint jp, Object bean, ResponseEntity result) throws Throwable {
        String resultEntity = new ObjectMapper().writeValueAsString(result);
        logger.info("Response object: \n" + resultEntity);
    }
}