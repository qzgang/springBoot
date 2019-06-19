package com.hrb.sso.common.annotation;


import com.hrb.sso.common.enums.DataSourceEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
   DataSourceEnum value() default DataSourceEnum.DB1;
}

