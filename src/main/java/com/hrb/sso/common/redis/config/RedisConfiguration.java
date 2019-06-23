package com.hrb.sso.common.redis.config;
import com.google.common.collect.Sets;
import com.hrb.sso.common.zookeeper.config.ZkConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Set;

/**
 *
 */
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {
    @Autowired
    ZkConfig zkConfig;

    @Bean(name="jedisCluster")
    public JedisClusterFactory jedisCluster(
           @Value("${redis.cluster.redis-server}") String redis_servers,
           @Value("${redis.cluster.connection-timeout}") int connectionTimeout,
           @Value("${redis.cluster.so-timeout}")int soTimeout,
           @Value("${redis.cluster.max-redirections}") int maxRedirections) {
        JedisClusterFactory jedisClusterFactory = new JedisClusterFactory();
        jedisClusterFactory.setConnectionTimeout(connectionTimeout);
        jedisClusterFactory.setSoTimeout(soTimeout);
        jedisClusterFactory.setMaxRedirections(maxRedirections);
        String[] split = null;
        if(StringUtils.isEmpty(zkConfig.getRedis_server())){
            split = redis_servers.split(",");
        }else{
            split = zkConfig.getRedis_server().split(",");
        }
        Set<String> hosts = Sets.newHashSet();
        Collections.addAll(hosts, split);
        jedisClusterFactory.setJedisClusterNodes(hosts);
        return jedisClusterFactory;
    }
}

