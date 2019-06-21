package com.hrb.sso.common.datasource.aop;

import com.hrb.sso.common.datasource.annotation.DataSource;
import com.hrb.sso.common.datasource.multiple.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author
 */
@Component
@Slf4j
@Aspect
@Order(-1)
public class DataSourceAspect {
    @Pointcut("@within(com.hrb.sso.common.annotation.DataSource) || @annotation(com.hrb.sso.common.annotation.DataSource)")
    public void pointCut(){

    }

    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource){
        System.out.println("选择数据源---"+dataSource.value().getValue());
        DataSourceContextHolder.setDataSource(dataSource.value().getValue());
    }

    @After("pointCut()")
    public void doAfter(){
        DataSourceContextHolder.clear();
    }
}
