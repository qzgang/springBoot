package com.hrb.sso.rz.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hrb.sso.common.redis.ICached;
import com.hrb.sso.common.zookeeper.config.ZkConfig;
import com.hrb.sso.rz.entity.RzBusRecord;
import com.hrb.sso.rz.service.RzBusRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 统一操作记录表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-06-20
 */
@Slf4j
@RestController
@RequestMapping("/rzBusRecord")
public class RzBusRecordController {
    @Autowired
    private RzBusRecordService recordService;

    @Autowired
    ZkConfig zkConfig;
    @Autowired
    ICached iCached;

    @GetMapping("/list")
    public List<RzBusRecord> list(){
        log.info("=====");
        Wrapper<RzBusRecord> wrapper = new EntityWrapper<>();
        return recordService.selectList(wrapper);
    }

    @GetMapping("/zk")
    public ZkConfig zk(){
        log.info("=====");
        return zkConfig;
    }

   @GetMapping("/icache")
    public Object icache() throws Exception{
        log.info("=====");
        return iCached.get("SERCURITY_MONEY");
    }
}

