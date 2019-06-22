package com.hrb.sso.common.datasource.annotation;


import com.hrb.sso.common.datasource.enums.DataSourceEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {




   DataSourceEnum value() default DataSourceEnum.ORACLE_DB;
}

