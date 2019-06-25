package com.hurbao.sso;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.net.URLDecoder;

/**
 * @Description: 代码生成器
 * @author qzg
 */
public class CodeGeneration {
    /**
     * @Title: main
     * @Description: 生成
     * @param args
     */
    public static void main(String[] args) throws Exception{

        String targetPath = Class.class.getClass().getResource("/").getPath();
        String currrntPath = URLDecoder.decode(targetPath.substring(0, targetPath.length() - 20), "utf-8");
        System.out.println(currrntPath);///E:/springBoot-workspace/hurbao-sso/
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(currrntPath + "/src/main/java");//输出文件路径
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sDao");
        gc.setXmlName("%sDao");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.ORACLE);
        dsc.setDriverName("oracle.jdbc.OracleDriver");
        dsc.setUsername("hurbao001_zl");
        dsc.setPassword("hurbao001_zl");
//        dsc.setUrl("jdbc:mysql://10.0.0.53/dev_hurp2p_zl?useUnicode=true&characterEncoding=utf8");
        dsc.setUrl("jdbc:oracle:thin:@10.0.0.215:1521:hurbao");
        dsc.setTypeConvert(new OracleTypeConvert(){
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                String t = fieldType.toUpperCase();
                if (t.contains("CHAR")) {
                    return DbColumnType.STRING;
                } else if (!t.contains("DATE") && !t.contains("TIMESTAMP")) {
                    if (t.contains("NUMBER")) {
                        if(t.matches("NUMBER")){
                            return DbColumnType.INTEGER;
                        }if (t.matches("NUMBER\\(+\\d\\)")) {
                            return DbColumnType.INTEGER;
                        } else {
                            return t.matches("NUMBER\\(+\\d{2}+\\)") ? DbColumnType.LONG : DbColumnType.DOUBLE;
                        }
                    } else if (t.contains("FLOAT")) {
                        return DbColumnType.FLOAT;
                    } else if (t.contains("clob")) {
                        return DbColumnType.CLOB;
                    } else if (t.contains("BLOB")) {
                        return DbColumnType.OBJECT;
                    } else if (t.contains("binary")) {
                        return DbColumnType.BYTE_ARRAY;
                    } else {
                        return t.contains("RAW") ? DbColumnType.BYTE_ARRAY : DbColumnType.STRING;
                    }
                } else {
                    return DbColumnType.DATE;
                }
            }
        });
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[] { "sys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[] { "SYS_CITY" }); // 需要生成的表
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        strategy.setEntityLombokModel(true);
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        mpg.setStrategy(strategy);

        // 包配置
        String[] tables = strategy.getInclude();
        String str = tables[0].toLowerCase().split("_")[0];
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.hurbao.sso"+"."+str);
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        pc.setEntity("entity");
        pc.setXml("dao");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }
}
