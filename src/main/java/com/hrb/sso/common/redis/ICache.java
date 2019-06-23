package com.hrb.sso.common.redis;

import com.hrb.sso.common.redis.service.RedisCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 对外提供访问redis操作
 */
@Service("iCache")
public final class ICache {
    private static Logger logger = LoggerFactory.getLogger(ICache.class);
    // 超时时间，单位为秒
    private final int expiration = 3600;
    @Resource
    private RedisCacheService redisCacheService;

    public Object getCache(String cacheKey) {
        return redisCacheService.getCache(cacheKey);
    }

    public boolean putCache(String cacheKey, Object objValue) {
        return putCache(cacheKey, objValue, expiration);
    }

    public boolean putCache(String cacheKey, Object objValue, int expiration) {
        try {
            if (expiration <= 0) {
                expiration = this.expiration;
            }
            return redisCacheService.putCache(cacheKey.toString(), objValue, expiration);
        } catch (Throwable ex) {
            logger.error("Cannot set the cache with the cacheKey:" + cacheKey.toString(), ex);
        }
        return false;
    }

    public boolean removeCache(String cacheKey) {
        try {
            redisCacheService.removeCache(cacheKey);
            return true;
        } catch (Throwable ex) {
            logger.error("Cannot remove the cache with the cacheKey:" + cacheKey.toString(), ex);
            return false;
        }
    }

    /**
     * 将对象以list集合形式存放
     *
     * @param cacheKey
     * @param objValue
     * @param objValue
     * @return
     */
    public boolean putListCache(String cacheKey, Object objValue) {
        try {
            return redisCacheService.putListCache(cacheKey, objValue);
        } catch (Throwable ex) {
            logger.error("Cannot put the list cache with the cacheKey:" + cacheKey.toString(), ex);
            return false;
        }
    }

    /**
     * 将对象存放在list集合指定index位置
     *
     * @param cacheKey
     * @param objValue
     * @param index
     * @return
     */
    public boolean putListCache(String cacheKey, Object objValue, int index) {
        try {
            return redisCacheService.putListCache(cacheKey, objValue, index);
        } catch (Throwable ex) {
            logger.error("Cannot put the list cache in the index with the cacheKey:" + cacheKey.toString(), ex);
            return false;
        }
    }

    /**
     * 获取list集合全部结果集
     *
     * @param cacheKey
     * @return
     */
    public List<Object> getListCache(String cacheKey) {
        try {
            return redisCacheService.getListCache(cacheKey);
        } catch (Throwable ex) {
            logger.error("Cannot get the list cache with the cacheKey:" + cacheKey.toString(), ex);
            return null;
        }
    }

    /**
     * 获取指定索引段内的集合
     *
     * @param cacheKey
     * @return
     */
    public List<Object> getListCache(String cacheKey, int start, int end) {
        try {
            return redisCacheService.getListCache(cacheKey, start, end);
        } catch (Throwable ex) {
            logger.error("Cannot get the list cache between start and end with the cacheKey:" + cacheKey.toString(), ex);
            return null;
        }
    }

    /**
     * 删减list集合指定坐标范围内数据
     *
     * @param cacheKey
     * @param start    起始坐标
     * @param end      结束坐标
     * @return
     */
    public boolean trimListCache(String cacheKey, int start, int end) {
        try {
            return redisCacheService.trimListCache(cacheKey, start, end);
        } catch (Throwable ex) {
            logger.error("Cannot get the list cache with the cacheKey:" + cacheKey.toString(), ex);
            return false;
        }
    }

    /**
     * 存放map集合缓存
     *
     * @param cacheKey
     * @param map
     * @return
     */
    public boolean putMapCache(String cacheKey, Map map) {
        try {
            return redisCacheService.putMapCache(cacheKey, map);
        } catch (Throwable ex) {
            logger.error("Cannot put the map cache with the cacheKey:" + cacheKey.toString(), ex);
            return false;
        }
    }

    /**
     * 删除Map集合中指定key-value
     *
     * @param cacheKey
     * @param mapKey
     * @return
     */
    public boolean deleteMapCache(String cacheKey, String mapKey) {
        try {
            return redisCacheService.deleteMapCache(cacheKey, mapKey);
        } catch (Throwable ex) {
            logger.error("Cannot delete the map cache with the cacheKey:" + cacheKey.toString() + " and the mapKey:" + mapKey, ex);
            return false;
        }
    }

    /**
     * 根据key查询集合中的value值
     *
     * @param cacheKey
     * @param mapKey
     * @return
     */
    public Object getMapValueCache(String cacheKey, String mapKey) {
        try {
            return redisCacheService.deleteMapCache(cacheKey, mapKey);
        } catch (Throwable ex) {
            logger.error("Cannot get the map value cache with the cacheKey:" + cacheKey.toString() + " and the mapKey:" + mapKey, ex);
            return false;
        }
    }
}

