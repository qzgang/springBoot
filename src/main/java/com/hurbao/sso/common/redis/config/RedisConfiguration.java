package com.hurbao.sso.common.redis.config;
import com.google.common.collect.Sets;
import com.hurbao.sso.common.zookeeper.cache.ZkProjectCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Set;

/**
 *
 */
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    @Bean(name="jedisCluster")
    public JedisClusterFactory jedisCluster(
           @Value("${redis.cluster.connection-timeout}") int connectionTimeout,
           @Value("${redis.cluster.so-timeout}")int soTimeout,
           @Value("${redis.cluster.max-redirections}") int maxRedirections) {
        JedisClusterFactory jedisClusterFactory = new JedisClusterFactory();
        jedisClusterFactory.setConnectionTimeout(connectionTimeout);
        jedisClusterFactory.setSoTimeout(soTimeout);
        jedisClusterFactory.setMaxRedirections(maxRedirections);
        String[] split = ZkProjectCache.getVal("redis.server").split(",");
        Set<String> hosts = Sets.newHashSet();
        Collections.addAll(hosts, split);
        jedisClusterFactory.setJedisClusterNodes(hosts);
        return jedisClusterFactory;
    }
}

