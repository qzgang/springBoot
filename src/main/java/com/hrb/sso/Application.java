package com.hrb.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.zookeeper.config.ZookeeperConfigAutoConfiguration;

/**
 * @author
 */
@SpringBootApplication(exclude = ZookeeperConfigAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
