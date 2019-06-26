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

    @Pointcut("execution(* com.hurbao..*.*ServiceImpl.*(..)) || execution(* com.hurbao..*.service.*Service.*(..)) " +
            "|| execution(* com.baomidou.mybatisplus.extension.service.IService.*(..)) || execution(* com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.*(..))")
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
            if(null ==annotation){
                DataSourceContextHolder.setDataSource(DataSourceEnum.ORACLE_DB.getValue());
            }else{
                DataSourceContextHolder.setDataSource(annotation.value().getValue());
            }
            if(log.isDebugEnabled()){
                log.debug("====数据库类型："+DataSourceContextHolder.getDataSource());
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