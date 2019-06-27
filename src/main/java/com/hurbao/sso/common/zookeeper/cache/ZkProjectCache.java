package com.hurbao.sso.common.zookeeper.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ZkListener 监听刷新
 * 缓存zookeeper /hurbao/conf/xxx/ xxx工程节点
 */
@Slf4j
public class ZkProjectCache {
    private  static Map<String,String> app_cache = null;
    private  static ReentrantReadWriteLock app_lock = new ReentrantReadWriteLock();

    public static void  putAppCache(String node,String value){
        try {
            app_lock.writeLock().lock();
            if(app_cache == null){
                app_cache = new HashMap<>(10);
            }
            app_cache.put(node,value);
        }catch (Exception e){
            log.error("putAppCache error!",e);
        }finally {
            app_lock.writeLock().unlock();
        }
    }

    public static void removeAppCache(String node){
        try {
            app_lock.writeLock().lock();
            if(app_cache == null){
                return;
            }
            app_cache.remove(node);
        }catch (Exception e){
            log.error("removeAppCache error!",e);
        }finally {
            app_lock.writeLock().unlock();
        }
    }

    public static String getVal(String key){
        return app_cache.get(key);
    }
}
