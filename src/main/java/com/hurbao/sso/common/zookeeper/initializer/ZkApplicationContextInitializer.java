package com.hurbao.sso.common.zookeeper.initializer;

import com.hurbao.sso.common.zookeeper.cache.ZkProjectCache;
import org.springframework.cloud.zookeeper.config.ZookeeperPropertySource;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.Collection;

/**
 * 初始化zookeeper /hurbao/conf/xxx/ xxx工程节点
 */
@Order
public class ZkApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        StandardEnvironment environment = (StandardEnvironment)applicationContext.getEnvironment();
        String[] keys = getKeys(environment);
        for(String key:keys){
            ZkProjectCache.putAppCache(key,environment.getProperty(key));
        }
    }

    private  String [] getKeys( StandardEnvironment environment){
        //zk节点
        String root = environment.getProperty("spring.cloud.zookeeper.config.root");
        String appName = environment.getProperty("spring.application.name");
        CompositePropertySource source = (CompositePropertySource)environment.getPropertySources().get("bootstrapProperties");
        Collection<PropertySource<?>> list =  source.getPropertySources();
        for(PropertySource propertySource : list){
            if(propertySource.getName().equals("zookeeper")){
                CompositePropertySource s = (CompositePropertySource)propertySource;
                Collection<PropertySource<?>> list1 =   s.getPropertySources();
                for(PropertySource propertySource2 : list1){
                    if(propertySource2.getName().equals(root+"/"+appName)){
                        ZookeeperPropertySource zkP = (ZookeeperPropertySource)propertySource2;
                        String [] names = zkP.getPropertyNames();
                        return names;
                    }
                    continue;
                }
            }
            continue;
        }
        return null;
    }
}
