package com.hrb.sso.rz.service.impl;

import com.hrb.sso.common.annotation.DataSource;
import com.hrb.sso.common.enums.DataSourceEnum;
import com.hrb.sso.rz.entity.RzBusRecord;
import com.hrb.sso.rz.dao.RzBusRecordDao;
import com.hrb.sso.rz.service.RzBusRecordService;
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
