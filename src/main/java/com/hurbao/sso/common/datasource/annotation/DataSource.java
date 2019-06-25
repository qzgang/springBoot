package com.hurbao.sso.common.datasource.annotation;


import com.hurbao.sso.common.datasource.enums.DataSourceEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
   DataSourceEnum value() default DataSourceEnum.ORACLE_DB;
}

