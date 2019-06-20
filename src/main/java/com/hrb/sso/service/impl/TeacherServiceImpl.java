package com.hrb.sso.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hrb.sso.common.annotation.DataSource;
import com.hrb.sso.entity.Teacher;
import com.hrb.sso.common.enums.DataSourceEnum;
import com.hrb.sso.mapper.TeacherMapper;
import com.hrb.sso.service.TeacherService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper,Teacher> implements TeacherService {

    @Override
    @DataSource(DataSourceEnum.MYSQL_DB)
    public boolean insert(Teacher entity) {
        return super.insert(entity);
    }

    @Override
    @DataSource(DataSourceEnum.MYSQL_DB)
    public boolean deleteById(Serializable id) {
        return super.deleteById(id);
    }

    @Override
    @DataSource(DataSourceEnum.MYSQL_DB)
    public boolean updateById(Teacher entity) {
        return super.updateById(entity);
    }

    @Override
    @DataSource(DataSourceEnum.MYSQL_DB)
    public Teacher selectById(Serializable id) {
        return super.selectById(id);
    }

    @Override
    @DataSource(DataSourceEnum.MYSQL_DB)
    public List<Teacher> selectList(Wrapper<Teacher> wrapper) {
        return super.selectList(wrapper);
    }

    @Override
    @DataSource(DataSourceEnum.MYSQL_DB)
    public Page<Teacher> selectPage(Page<Teacher> page) {
        return super.selectPage(page);
    }

    @Override
    @DataSource(DataSourceEnum.MYSQL_DB)
    public Page<Teacher> selectPage(Page<Teacher> page, Wrapper<Teacher> wrapper) {
        return super.selectPage(page, wrapper);
    }
}

