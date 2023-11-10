package com.fulinlin.generator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.querys.KingbaseESQuery;
import com.baomidou.mybatisplus.generator.config.querys.MariadbQuery;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.keywords.PostgreSqlKeyWordsHandler;
import com.baomidou.mybatisplus.generator.query.SQLQuery;
import com.fulinlin.generator.config.FileConfig;
import com.fulinlin.generator.config.GenerateConfig;

/**
 * @program: database-doc
 * @author: fulin
 * @create: 2019-01-13 15:37
 **/
public class GenerateTest {
    public static void main(String[] args) {
        GenerateConfig generateConfig = new GenerateConfig();
        generateConfig.setDataSourceConfig(new DataSourceConfig.Builder(
                        "jdbc:mariadb://127.0.0.1:3306/test",
                        "root",
                        "wangle")
                        //.schema("PUBLIC")
                        .dbQuery(new MariadbQuery())
                        .keyWordsHandler(new PostgreSqlKeyWordsHandler())
                        .databaseQueryClass(SQLQuery.class)
                        .build())
                .setStrategyConfig(new StrategyConfig.Builder()
                        .enableCapitalMode()
                        .enableSkipView()
                        .disableSqlFilter()
                        .build())
                .setFileConfig(new FileConfig()
                        .setOutFileName("测试")
                        .setOutDir("/Users/fulin/Desktop"));
        //***************生成配置***************/
        generateConfig.execute();
    }
}
