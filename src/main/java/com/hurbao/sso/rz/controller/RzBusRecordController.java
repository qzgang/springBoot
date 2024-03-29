package com.hurbao.sso.rz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hurbao.sso.common.redis.ICached;
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
    ICached iCached;

    @GetMapping("/list")
    public List<RzBusRecord> list(){
        QueryWrapper wrapper = new QueryWrapper();
        RzBusRecord entity = new RzBusRecord();
        entity.setId("1");
        wrapper.setEntity(entity);
        return recordService.list(wrapper);
    }

    @GetMapping("/page")
    public List<RzBusRecord> page(){
        Page page = new Page(1,2);
        return recordService.page(page).getRecords();
    }

   @GetMapping("/icache")
    public Object icache() throws Exception{
        log.info("=====");
        return iCached.get("SERCURITY_MONEY");
    }
}

