package com.hrb.sso;

import com.hrb.sso.common.zookeeper.ZkConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * @author
 */


@SpringBootApplication
@EnableConfigurationProperties(ZkConfig.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
