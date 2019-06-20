package com.hrb.sso.common.multiple;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * @author qzg
 */
public class MultipleDataSource  extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
