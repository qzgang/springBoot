package com.hurbao.sso.common.zookeeper.cache;


import com.hurbao.sso.sys.service.impl.CityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 存放节点缓存  /hurbao/conf/cache_notice/
 */
@Slf4j
@Component
public class ZkNoticeCache {
    @Autowired
    CityServiceImpl cityService;
    /**
     * 缓存zookeeper节点 /hurbao/conf/cache_notice/
     */
    private  Map<String,String> notice_cache = null;

    private  ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void putNoticeCache(String node,String value){
        try {
            lock.writeLock().lock();
            if(notice_cache == null) {
                notice_cache = new HashMap<>(10);
            }
            notice_cache.put(node, value);
            this.initBusinessCache(node);
        }catch (Exception e){
            log.error("putNoticeCache error!",e);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public  void removeNoticeCache(String node){
        try {
            lock.writeLock().lock();
            if(notice_cache == null){
                return;
            }
            notice_cache.remove(node);
        }catch (Exception e){
            log.error("removeNoticeCache error!",e);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public String getVal(String key){
        return notice_cache.get(key);
    }

    private void initBusinessCache(String node){
        switch (node){
            case "cache_bank_err":
            case "cache_ps_param":
            case "cache_category_info":
            case "cache_city_info":
                cityService.initCity();
            case "cache_data_dict":
            case "cache_holiday_conf":
            case "cache_mac_user_share_template":
            case "cache_message_tempalte":
            case "cache_mgr_purview":
            case "cache_page_keys":
            case "cache_platform_account":
            case "cache_remind_message":
            case "cache_settle_task_cron":
            case "cache_sso_app":
            case "cache_sso_key":
            case "cache_supplier_info":
        }
    }
}
