package com.hurbao.sso.common.datasource.aop;

import com.hurbao.sso.common.datasource.annotation.DataSource;
import com.hurbao.sso.common.datasource.enums.DataSourceEnum;
import com.hurbao.sso.common.datasource.multiple.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author
 */
@Component
@Slf4j
@Aspect
public class DataSourceAspect {

    @Pointcut("execution(* com.hurbao..*.*ServiceImpl.*(..)) || execution(* com.hurbao..*.service.*Service.*(..))")
    public void pointCut(){
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        try {
            if (null ==annotation) {
                Object target=pjp.getTarget();
                annotation = target.getClass().getAnnotation(DataSource.class);
            }
            if(log.isDebugEnabled()){
                log.debug("get annotation for  "+pjp.getTarget().getClass()+"  ");
            }
            if(null ==annotation){
                DataSourceContextHolder.setDataSource(DataSourceEnum.ORACLE_DB.getValue());
            }else{
                DataSourceContextHolder.setDataSource(annotation.value().getValue());
            }
            retVal = pjp.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            DataSourceContextHolder.clear();
        }
        return retVal;

    }
}