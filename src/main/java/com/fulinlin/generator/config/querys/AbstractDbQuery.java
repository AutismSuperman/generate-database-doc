package com.fulinlin.generator.config.querys;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author fulin
 * @since 2016/8/30
 **/
abstract class AbstractDbQuery {
    /**
     * 得到每一个表中的数据
     *
     * @param conn      连接
     * @param tableName 表名
     * @return
     */
    abstract List<Map<String, Object>> getColumnList(Connection conn, String tableName) throws SQLException;


    /**
     * 构建好一个map
     */
    public Map<String, Object> generateMap(List<Map<String, Object>> list) {
        return Collections.singletonMap("table", list);
    }


}
