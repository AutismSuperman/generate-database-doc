package com.fulinlin.generator;

import com.fulinlin.generator.config.DataSourceConfig;
import com.fulinlin.generator.config.FileConfig;
import com.fulinlin.generator.enums.DbType;

/**
 * @program: database-doc
 * @author: fulin
 * @create: 2019-01-13 15:37
 **/
public class xxx {
    public static void main(String[] args) {
        //***************数据库配置***************/
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig
                .setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("123456")
                .setUrl("jdbc:mysql://127.0.0.1:3306/dbm");
        //***************文件输出配置***************/
        FileConfig fileConfig = new FileConfig();
        fileConfig.setOutDir("D:/").setOutFileName("测试");
        //***************生成配置***************/
        GenerateConfig generateConfig = new GenerateConfig();
        generateConfig.setDataSourceConfig(dataSourceConfig).setFileConfig(fileConfig).execute();
    }
}
