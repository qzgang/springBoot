package com.hurbao.sso.common.zookeeper.listener;

import com.hurbao.sso.common.zookeeper.cache.ZkNoticeCache;
import com.hurbao.sso.common.zookeeper.cache.ZkProjectCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

import static org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type.*;

/**
 * 节点zookeeper监听
 * 参数任意(为Object）的时候所有事件都会监听到
 * 所有，该参数事件，或者其子事件（子类）都可以接收到
 */
@Slf4j
@Component
public class ZkListener {
    @Autowired(required = false)
    ZkNoticeCache zkNoticeCache;
    @Autowired
    Environment environment;
    /**
     *  监听RefreshEvent事件
     * @param event
     */
    @EventListener
    public void event(RefreshEvent event){
        String root = environment.getProperty("spring.cloud.zookeeper.config.root");
        String defaultContext =  environment.getProperty("spring.cloud.zookeeper.config.defaultContext");
        String appName = environment.getProperty("spring.application.name");
        String CACHE_NOTICE_PATH = root+"/"+defaultContext;
        String CACHE_APP_PATH = root+"/"+appName;

        if(event.getEvent() instanceof TreeCacheEvent){
            TreeCacheEvent treeCacheEvent =  (TreeCacheEvent) event.getEvent();
            //节点操作类型
            TreeCacheEvent.Type eventType = treeCacheEvent.getType();
            //节点路径
            String path = treeCacheEvent.getData().getPath();
            //数据
            String value = new String(treeCacheEvent.getData().getData(), Charset.forName("UTF-8"));
            //增加or更新节点
            if(eventType == NODE_ADDED || eventType == NODE_UPDATED) {
                if(path.startsWith(CACHE_NOTICE_PATH)){
                    String node = path.replaceAll(CACHE_NOTICE_PATH,"");
                    if(zkNoticeCache !=null){
                        zkNoticeCache.putNoticeCache(node,value);
                        if(eventType == NODE_ADDED ){
                            log.info("===>zookeeper添加缓存path={}，data={}",path,value);
                        }else{
                            log.info("===>zookeeper更新缓存path={}，data={}",path,value);
                        }
                    }
                }
                if(path.startsWith(CACHE_APP_PATH)){
                    String node = path.replaceAll(CACHE_APP_PATH,"");
                    ZkProjectCache.putAppCache(node,value);
                    if(eventType == NODE_ADDED ){
                        log.info("===>zookeeper添加缓存path={}，data={}",path,value);
                    }else{
                        log.info("===>zookeeper更新缓存path={}，data={}",path,value);
                    }
                }

            }
            //删除节点
            if(eventType == NODE_REMOVED){
                if(path.startsWith(CACHE_NOTICE_PATH)){
                    String node = path.replaceAll(CACHE_NOTICE_PATH,"");
                    if(zkNoticeCache !=null){
                        zkNoticeCache.removeNoticeCache(node);
                        log.info("===>zookeeper删除缓存path={}",path);
                    }
                }
                if(path.startsWith(CACHE_APP_PATH)){
                    String node = path.replaceAll(CACHE_APP_PATH,"");
                    ZkProjectCache.removeAppCache(node);
                    log.info("===>zookeeper删除缓存path={}",path);
                }
            }
        }
    }
}
