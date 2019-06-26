package com.hurbao.sso.common.zookeeper.listener;

import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * zookeeper节点监听
 * 参数任意(为Object）的时候所有事件都会监听到
 * 所有，该参数事件，或者其子事件（子类）都可以接收到
 */
@Component
public class ZkListener {

    /**
     * 节点更新后
     * @param event
     */
    @EventListener
    public void event(EnvironmentChangeEvent event){
        System.out.println("MyEventHandle 接收到事件：" + event.getClass());
    }


    /**
     * 节点更新前
     * @param event
     */
    @EventListener
    public void event(RefreshEvent event){
        System.out.println("MyEventHandle 接收到事件：" + event.getEventDesc());
        System.out.println("MyEventHandle 接收到事件：" + event.getSource());
    }
}
