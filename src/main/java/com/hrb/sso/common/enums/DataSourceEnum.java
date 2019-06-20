package com.hrb.sso.common.enums;

/**
 * @author
 */
public enum DataSourceEnum {
    ORACLE_DB("oracleDb"),MYSQL_DB("mysqlDb");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
