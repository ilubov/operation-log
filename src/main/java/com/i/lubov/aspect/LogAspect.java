package com.i.lubov.aspect;

import java.lang.reflect.Method;

import com.i.lubov.annotation.Log;
import com.i.lubov.comps.OperationLogEvent;
import com.i.lubov.enums.LogType;
import com.i.lubov.util.EventUtils;
import com.i.lubov.util.HttpContextUtils;
import com.i.lubov.util.ThrowableUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LogAspect {
    @Value("${lubov.log.enabled:false}")
    private Boolean logEnabled;

    public LogAspect() {}

    @Pointcut("@annotation(com.i.lubov.annotation.Log)")
    public void logPointcut() {}

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!this.logEnabled) {
            return joinPoint.proceed();
        } else {
            long startTime = System.currentTimeMillis();
            OperationLogEvent operationLogEvent = this.buildCommonLogEvent(joinPoint);
            Object result = joinPoint.proceed();
            operationLogEvent.setLogType(LogType.INFO.getCode())
                    .setExecuteTime(System.currentTimeMillis() - startTime);
            EventUtils.postEvent(operationLogEvent);
            return result;
        }
    }

    private OperationLogEvent buildCommonLogEvent(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        StringBuilder params = new StringBuilder("{");
        Object[] argValues = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; ++i) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        params.append(" }");
        OperationLogEvent operationLogEvent = new OperationLogEvent();
        operationLogEvent.setBrowser(HttpContextUtils.getBrowser())
                .setDescription(logAnnotation.value())
                .setMethod(methodName).setParams(params.toString())
                .setRequestIp(HttpContextUtils.getRequestIp())
                .setUsername("currentUserName");
        return operationLogEvent;
    }

    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void operationLogAfterThrowing(JoinPoint joinPoint, Throwable e) {
        if (this.logEnabled) {
            long startTime = System.currentTimeMillis();
            OperationLogEvent operationLogEvent = this.buildCommonLogEvent(joinPoint);
            operationLogEvent.setLogType(LogType.ERROR.getCode())
                    .setExceptionDetail(ThrowableUtils.getStackTrace(e))
                    .setExecuteTime(System.currentTimeMillis() - startTime);
            EventUtils.postEvent(operationLogEvent);
        }
    }
}
