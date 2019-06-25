package com.hurbao.sso.rz.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hurbao.sso.common.redis.ICached;
import com.hurbao.sso.common.zookeeper.config.ZkConfig;
import com.hurbao.sso.rz.entity.RzBusRecord;
import com.hurbao.sso.rz.service.RzBusRecordService;
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
        Wrapper<RzBusRecord> wrapper = new QueryWrapper<>();
        return recordService.list();
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

