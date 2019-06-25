package com.hurbao.sso.rz.service.impl;

import com.hurbao.sso.common.datasource.annotation.DataSource;
import com.hurbao.sso.common.datasource.enums.DataSourceEnum;
import com.hurbao.sso.rz.entity.RzBusRecord;
import com.hurbao.sso.rz.dao.RzBusRecordDao;
import com.hurbao.sso.rz.service.RzBusRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 统一操作记录表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-20
 */
@Service
@DataSource(DataSourceEnum.MYSQL_DB)
public class RzBusRecordServiceImpl extends ServiceImpl<RzBusRecordDao, RzBusRecord> implements RzBusRecordService {

}
