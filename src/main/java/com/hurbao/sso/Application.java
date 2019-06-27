package com.hurbao.sso;

import com.hurbao.sso.common.zookeeper.initializer.ZkApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 程序入口
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        //加载zk数据
        application.addInitializers(new ZkApplicationContextInitializer());
        application.run(args);
    }

}
