package com.fulinlin.generator.query;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.querys.DbQueryDecorator;
import com.baomidou.mybatisplus.generator.jdbc.DatabaseMetaDataWrapper;
import com.fulinlin.generator.entity.TableInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fulin
 * @since 2016/8/30
 **/
public class TableInfoQuery {

    /**
     * 得到每一个表中的数据
     */
    public List<TableInfo> getTableList(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig) throws SQLException {
        DbQueryDecorator dbQueryDecorator = new DbQueryDecorator(dataSourceConfig, strategyConfig);
        List<TableInfo> tableList = new ArrayList<>();
        try {
            String schemaName = dataSourceConfig.getSchemaName();
            DatabaseMetaDataWrapper databaseMetaDataWrapper =
                    new DatabaseMetaDataWrapper(dbQueryDecorator.getConnection(), schemaName);
            String sql = dbQueryDecorator.tablesSql();
            dbQueryDecorator.execute(sql, result -> {
                String tableName = result.getStringResult(dbQueryDecorator.tableName());
                DatabaseMetaDataWrapper.Table table = databaseMetaDataWrapper.getTableInfo(tableName);
                Map<String, DatabaseMetaDataWrapper.Column> columns =
                        databaseMetaDataWrapper.getColumnsInfo(tableName, true);
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTable(table);
                tableInfo.setColumns(new ArrayList<>(columns.values()));
                tableList.add(tableInfo);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 数据库操作完成,释放连接对象
            dbQueryDecorator.closeConnection();
        }
        return tableList;
    }


}
