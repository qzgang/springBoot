package com.hrb.sso.rz.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hrb.sso.entity.Teacher;
import com.hrb.sso.rz.entity.RzBusRecord;
import com.hrb.sso.rz.service.RzBusRecordService;
import com.hrb.sso.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
@RestController
@RequestMapping("/rzBusRecord")
public class RzBusRecordController {

    @Autowired
    private RzBusRecordService recordService;

    @GetMapping("/list")
    public List<RzBusRecord> list(){
        Wrapper<RzBusRecord> wrapper = new EntityWrapper<>();
        return recordService.selectList(wrapper);
    }
}

