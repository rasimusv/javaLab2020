package ru.itis.rasimusv.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.itis.rasimusv.dto.MethodCallDto;
import ru.itis.rasimusv.services.MethodCallLogService;

import java.util.Date;

@Component
@Aspect
public class MethodCallAspect {

    private final MethodCallLogService methodCallLogService;

    public MethodCallAspect(MethodCallLogService methodCallLogService) {
        this.methodCallLogService = methodCallLogService;
    }

    @Around(value = "execution(* ru.itis.rasimusv.controllers.*.*(..))")
    public Object writeMethodIsCalled(ProceedingJoinPoint joinPoint) throws Throwable{
        Date datetime = new Date(System.currentTimeMillis());
        Object output = joinPoint.proceed();
        methodCallLogService.addMethodCall(MethodCallDto.builder()
                .methodName(joinPoint.getTarget().getClass().getName())
                .callTime(datetime)
                .build());
        return output;
    }
}
