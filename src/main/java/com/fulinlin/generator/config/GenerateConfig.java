package com.fulinlin.generator.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.fulinlin.generator.engine.PoiTlWordEngine;
import com.fulinlin.generator.entity.TableInfo;
import com.fulinlin.generator.query.TableInfoQuery;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: database-doc
 * @author: fulin
 * @create: 2019-01-13 15:25
 **/
@Slf4j
@Data
@Accessors(chain = true)
public class GenerateConfig {

    private DataSourceConfig dataSourceConfig;

    private StrategyConfig strategyConfig;

    private FileConfig fileConfig;

    public void execute() {
        log.info("=========================================准备生成文件=========================================");
        try {
            TableInfoQuery tableInfoQuery = new TableInfoQuery();
            List<TableInfo> tableList = tableInfoQuery.getTableList(dataSourceConfig, strategyConfig);
            String fileName = fileConfig.getWriteFile();
            Map<String, Object> data = new HashMap<>();
            data.put("table", tableList);
            new PoiTlWordEngine().outputFile(data, fileConfig);
            log.info("文件位于===>{}", fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("=========================================生成文件结束=========================================");
    }
}
