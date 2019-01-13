package com.fulinlin.generator.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 表数据查询接口
 * 到时候就把查询字段等放这里做接口
 * </p>
 *
 * @author fulin
 * @since 2018-01-16
 */
public interface IDbQuery {
    /**
     * 查询和拼接所有表的小玩意
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    Map<String, Object> getTableList(Connection conn) throws SQLException;

    /**
     * 获取表所有表名sql
     *
     * @return
     */
    String tableNames();

    /**
     * 获取所有表结构详细信息的sql
     *
     * @return
     */
    String tableStructure(String tableName);
}
