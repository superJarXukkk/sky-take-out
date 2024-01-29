package com.sky.Aspect;





import com.sky.Annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.Annotation.AutoFill)")
    private void autoFillPointcut(){};


    @Before("autoFillPointcut()")
    public void finishAutoFill(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType type = autoFill.getOperationType();
        String name = signature.getName();
        log.info(name + "方法进入增强");


        Object[] args = joinPoint.getArgs();
        Object obj = args[0];
        if(obj != null){

            log.info("开始字段填充");
            Method setUpdateTime = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            Method setUpdateUser = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            setUpdateUser.invoke(obj, BaseContext.getCurrentId());
            setUpdateTime.invoke(obj,LocalDateTime.now());
            if(type.equals(OperationType.INSERT)){
                Method setCreateTime = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                setCreateTime.invoke(obj,LocalDateTime.now());
                setCreateUser.invoke(obj,BaseContext.getCurrentId());
            }

        }else return;
    }
}
