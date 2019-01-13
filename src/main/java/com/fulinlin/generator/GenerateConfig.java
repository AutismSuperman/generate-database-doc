package com.fulinlin.generator;

import com.fulinlin.generator.config.DataSourceConfig;
import com.fulinlin.generator.config.FileConfig;
import com.fulinlin.generator.config.IDbQuery;
import com.fulinlin.generator.utils.FreemarkerUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    private FileConfig fileConfig;

    public void execute() {
        log.info("=========================================准备生成文件=========================================");
        //拿到数据库连接
        Connection conn = dataSourceConfig.getConn();
        //拿到执行的query类
        IDbQuery dbQuery = dataSourceConfig.getDbQuery();
        //得到要给模版的数据
        Map<String, Object> tableList;
        try {
            tableList = dbQuery.getTableList(conn);
            Map<String, Object> map = Optional.of(tableList).orElse(new HashMap<>());
            String fileName = fileConfig.getOutFileName() + ".doc";
            FreemarkerUtil.getInstance().generateFile(fileConfig.getTemplatesDir(), fileConfig.getTemplateName(), map, fileConfig.getOutDir(), fileName);
            log.info("文件位于=>{}", fileConfig.getOutDir() + fileName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //生成文件
        log.info("=========================================生成文件结束=========================================");
    }
}
