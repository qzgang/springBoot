package com.hurbao.sso.common.datasource.p6spy;

import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * p6spy的SQL输出格式
 */
public class P6SpyLogger implements MessageFormattingStrategy {
    public P6SpyLogger() {
    }
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        sql = SqlUtils.sqlFormat(sql, true);
        return StringUtils.isNotEmpty(sql) ? " Consume Time：" + elapsed + " ms " + now + "\n Execute SQL：" + sql+ "\n" : null;
    }
}
