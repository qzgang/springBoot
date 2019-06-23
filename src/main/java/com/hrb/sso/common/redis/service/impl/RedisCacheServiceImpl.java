package com.hrb.sso.common.redis.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.hrb.sso.common.redis.service.RedisCacheService;
import com.hrb.sso.common.util.SerializingUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service("iCacheManager")
public class RedisCacheServiceImpl implements RedisCacheService {

    private static final String JEDIS_SET_RETURN_OK = "OK";

    @Resource
    private JedisCluster jedisCluster;

    @Override
    public Object getCache(Serializable cacheKey) {
        return SerializingUtil.deserialize((byte[]) jedisCluster.get(SerializingUtil.serialize(cacheKey)));
    }

    @Override
    public boolean putCache(Serializable cacheKey, Object objValue, int expiration) {
        String result = jedisCluster.setex(SerializingUtil.serialize(cacheKey), expiration, SerializingUtil.serialize(objValue));
        if (StringUtils.equals(JEDIS_SET_RETURN_OK, result)) {
            return true;
        }
        return false;
    }

    @Override
    public Long removeCache(Serializable cacheKey) {
        return jedisCluster.del(SerializingUtil.serialize(cacheKey));
    }

    @Override
    public boolean putListCache(Serializable cacheKey, Object objValue) {
        Long num = jedisCluster.rpush(SerializingUtil.serialize(cacheKey), SerializingUtil.serialize(objValue));
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean putListCache(Serializable cacheKey, Object objValue, int index) {
        String result = jedisCluster.lset(SerializingUtil.serialize(cacheKey), index, SerializingUtil.serialize(objValue));
        if (StringUtils.equals(JEDIS_SET_RETURN_OK, result)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Object> getListCache(Serializable cacheKey, int start, int end) {
        List<byte[]> list = jedisCluster.lrange(SerializingUtil.serialize(cacheKey), start, end);
        if (null != list && list.size() > 0) {
            List<Object> objList = new ArrayList<Object>();
            for (byte[] b : list) {
                objList.add(SerializingUtil.deserialize(b));
            }
            return objList;
        }
        return null;
    }

    @Override
    public List<Object> getListCache(Serializable cacheKey) {
        return getListCache(cacheKey, 0, -1);
    }

    @Override
    public boolean trimListCache(Serializable cacheKey, int start, int end) {
        String result = jedisCluster.ltrim(SerializingUtil.serialize(cacheKey), start, end);
        if (StringUtils.equals(JEDIS_SET_RETURN_OK, result)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean putMapCache(Serializable cacheKey, Map<Object, Object> map) {
        if (null != map && !map.isEmpty()) {
            Map<byte[], byte[]> byteMap = new HashMap<byte[], byte[]>();
            for (Entry<Object, Object> entry : map.entrySet()) {
                byteMap.put(SerializingUtil.serialize(entry.getKey()), SerializingUtil.serialize(entry.getValue()));
            }
            String result = jedisCluster.hmset(SerializingUtil.serialize(cacheKey), byteMap);
            if (StringUtils.equals(JEDIS_SET_RETURN_OK, result)) {
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMapCache(Serializable cacheKey, Serializable mapKey) {
        Long result = jedisCluster.hdel(SerializingUtil.serialize(cacheKey), SerializingUtil.serialize(mapKey));
        if (result > 0) {
            return true;
        }
        return false;
    }


    @Override
    public Object getMapValueCache(Serializable cacheKey, Serializable mapKey) {
        List<byte[]> list = jedisCluster.hmget(SerializingUtil.serialize(cacheKey), SerializingUtil.serialize(mapKey));
        if (null != list && list.size() > 0) {
            return SerializingUtil.deserialize(list.get(0));
        }
        return null;
    }

}
