package com.hrb.sso.common.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.hrb.sso.common.datasource.enums.DataSourceEnum;
import com.hrb.sso.common.datasource.multiple.MultipleDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qzg
 */
@Configuration
@MapperScan("com.hrb.sso.*.dao*")
public class MyBatiesPlusConfiguration {
    /**
     * 数据源oracle
     * @return
     */
    @Bean(name = "oracleDb")
    @ConfigurationProperties(prefix = "spring.datasource.druid.oracle-db" )
    public DataSource oracleDb() {
        return DruidDataSourceBuilder.create().build();
    }
    /**
     * 数据源mysql
     * @return
     */
    @Bean(name = "mysqlDb")
    @ConfigurationProperties(prefix = "spring.datasource.druid.mysql-db" )
    public DataSource mysqlDb() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("oracleDb") DataSource oracleDb, @Qualifier("mysqlDb") DataSource mysqlDb) {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map< Object,Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceEnum.ORACLE_DB.getValue(), oracleDb);
        targetDataSources.put(DataSourceEnum.MYSQL_DB.getValue(), mysqlDb);
        //添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(oracleDb);
        return multipleDataSource;
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    /**
     * SQL执行效率插件(设置dev,test 环境开启)
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000);
        //SQL是否格式化
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactory.setDataSource(multipleDataSource(oracleDb(),mysqlDb()));
        //设置配置
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        //设置插件
        sqlSessionFactory.setPlugins(new Interceptor[]{paginationInterceptor(),performanceInterceptor()});
        return sqlSessionFactory.getObject();
    }

    /*@Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(0);
        //conf.setMetaObjectHandler(new MyMetaObjectHandler());
        conf.setDbColumnUnderline(true);
        conf.setRefresh(true);
        return conf;
    }*/
}

