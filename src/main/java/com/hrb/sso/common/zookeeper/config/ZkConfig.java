package com.hrb.sso.common.zookeeper.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix ="hrb-test")
public class ZkConfig {
    public String oracle_jdbc_url;
    public String oracle_jdbc_username;
    public String oracle_jdbc_password;
    public String mysql_jdbc_url;
    public String mysql_jdbc_username;
    public String mysql_jdbc_password;
    public String redis_server;

}
