package com.hrb.sso.common.zookeeper;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config")
@Data
public class ZkConfig {
    public String username;
    public String password;
}
