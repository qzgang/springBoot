package com.hurbao.sso.common.zookeeper.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取zk上配置信息，自动刷新
 */
@Component
@ConfigurationProperties(prefix ="config")
public class ZkConfig {
    public String oracle_jdbc_url;
    public String oracle_jdbc_username;
    public String oracle_jdbc_password;
    public String mysql_jdbc_url;
    public String mysql_jdbc_username;
    public String mysql_jdbc_password;
    public String redis_server;

    public String getOracle_jdbc_url() {
        return oracle_jdbc_url;
    }

    public void setOracle_jdbc_url(String oracle_jdbc_url) {
        this.oracle_jdbc_url = oracle_jdbc_url;
    }

    public String getOracle_jdbc_username() {
        return oracle_jdbc_username;
    }

    public void setOracle_jdbc_username(String oracle_jdbc_username) {
        this.oracle_jdbc_username = oracle_jdbc_username;
    }

    public String getOracle_jdbc_password() {
        return oracle_jdbc_password;
    }

    public void setOracle_jdbc_password(String oracle_jdbc_password) {
        this.oracle_jdbc_password = oracle_jdbc_password;
    }

    public String getMysql_jdbc_url() {
        return mysql_jdbc_url;
    }

    public void setMysql_jdbc_url(String mysql_jdbc_url) {
        this.mysql_jdbc_url = mysql_jdbc_url;
    }

    public String getMysql_jdbc_username() {
        return mysql_jdbc_username;
    }

    public void setMysql_jdbc_username(String mysql_jdbc_username) {
        this.mysql_jdbc_username = mysql_jdbc_username;
    }

    public String getMysql_jdbc_password() {
        return mysql_jdbc_password;
    }

    public void setMysql_jdbc_password(String mysql_jdbc_password) {
        this.mysql_jdbc_password = mysql_jdbc_password;
    }

    public String getRedis_server() {
        return redis_server;
    }

    public void setRedis_server(String redis_server) {
        this.redis_server = redis_server;
    }
}
