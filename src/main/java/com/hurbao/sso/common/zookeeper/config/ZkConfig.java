package com.hurbao.sso.common.zookeeper.config;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取zk上配置信息，自动刷新
 */
@Data
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
}
