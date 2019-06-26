package com.hurbao.sso.common.zookeeper.config;


import com.hurbao.sso.sys.service.impl.CityServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取zk上cache-notice配置信息，自动刷新
 */
@Component
@ConfigurationProperties(prefix ="cache-notice-test")
public class ZkCacheNotice {
    @Autowired
    CityServiceImpl cityService;

    public String cache_bank_err;
    public String cache_ps_param;
    public String cache_category_info;
    public String cache_city_info;
    public String cache_holiday_conf;
    public String cache_mac_user_share_template;
    public String cache_message_tempalte;

    public String cache_mgr_purview;
    public String cache_platform_account;
    public String cache_remind_message;
    public String cache_settle_task_cron;
    public String cache_sso_app;
    public String cache_supplier_info;

    public String getCache_bank_err() {
        return cache_bank_err;
    }

    public void setCache_bank_err(String cache_bank_err) {
        this.cache_bank_err = cache_bank_err;
    }

    public String getCache_ps_param() {
        return cache_ps_param;
    }

    public void setCache_ps_param(String cache_ps_param) {
        this.cache_ps_param = cache_ps_param;
    }

    public String getCache_category_info() {
        return cache_category_info;
    }

    public void setCache_category_info(String cache_category_info) {
        this.cache_category_info = cache_category_info;
    }

    public String getCache_city_info() {
        return cache_city_info;
    }

    public void setCache_city_info(String cache_city_info) {
        this.cache_city_info = cache_city_info;
        cityService.initCity();
    }

    public String getCache_holiday_conf() {
        return cache_holiday_conf;
    }

    public void setCache_holiday_conf(String cache_holiday_conf) {
        this.cache_holiday_conf = cache_holiday_conf;
    }

    public String getCache_mac_user_share_template() {
        return cache_mac_user_share_template;
    }

    public void setCache_mac_user_share_template(String cache_mac_user_share_template) {
        this.cache_mac_user_share_template = cache_mac_user_share_template;
    }

    public String getCache_message_tempalte() {
        return cache_message_tempalte;
    }

    public void setCache_message_tempalte(String cache_message_tempalte) {
        this.cache_message_tempalte = cache_message_tempalte;
    }

    public String getCache_mgr_purview() {
        return cache_mgr_purview;
    }

    public void setCache_mgr_purview(String cache_mgr_purview) {
        this.cache_mgr_purview = cache_mgr_purview;
    }

    public String getCache_platform_account() {
        return cache_platform_account;
    }

    public void setCache_platform_account(String cache_platform_account) {
        this.cache_platform_account = cache_platform_account;
    }

    public String getCache_remind_message() {
        return cache_remind_message;
    }

    public void setCache_remind_message(String cache_remind_message) {
        this.cache_remind_message = cache_remind_message;
    }

    public String getCache_settle_task_cron() {
        return cache_settle_task_cron;
    }

    public void setCache_settle_task_cron(String cache_settle_task_cron) {
        this.cache_settle_task_cron = cache_settle_task_cron;
    }

    public String getCache_sso_app() {
        return cache_sso_app;
    }

    public void setCache_sso_app(String cache_sso_app) {
        this.cache_sso_app = cache_sso_app;
    }

    public String getCache_supplier_info() {
        return cache_supplier_info;
    }

    public void setCache_supplier_info(String cache_supplier_info) {
        this.cache_supplier_info = cache_supplier_info;
    }
}
