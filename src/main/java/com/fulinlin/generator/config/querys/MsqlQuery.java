package com.fulinlin.generator.config.querys;

import com.fulinlin.generator.config.IDbQuery;
import com.fulinlin.generator.utils.FreemarkerUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fulin
 * @since 2016/8/30
 **/
@Slf4j
public class MsqlQuery extends AbstractDbQuery implements IDbQuery {


    @Override
    public Map<String, Object> getTableList(Connection conn) throws SQLException {
        List<Map<String, Object>> tableList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(this.tableNames());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            String TABLE_NAME = rs.getString("NAME");
            String COMMENTS = rs.getString("COMMENT");
            map.put("TABLE_NAME", TABLE_NAME);
            map.put("COMMENTS", COMMENTS == null ? "" : COMMENTS.replaceAll("\r|\n\t", "").trim());
            List<Map<String, Object>> columnList = getColumnList(conn, TABLE_NAME);//获取列
            map.put("COLUMNS", columnList);
            //if (TABLE_NAME.startsWith("ZJ"))//过滤
            tableList.add(map);
            log.info("TABLE_NAME=>{}COMMENTS=>{}", TABLE_NAME, COMMENTS);
        }
        rs.close();
        ps.close();
        return generateMap(tableList);
    }

    @Override
    public List<Map<String, Object>> getColumnList(Connection conn, String tableName) throws SQLException {
        List<Map<String, Object>> columnList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(this.tableStructure(tableName));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            String COLUMN_NAME = rs.getString("FIELD");
            String DATA_TYPE = rs.getString("TYPE");//varchar(200)
            String DATA_LENGTH = "";//rs.getString("DATA_LENGTH");
            if (DATA_TYPE.contains("(")) {
                DATA_LENGTH = DATA_TYPE.substring(DATA_TYPE.indexOf("(") + 1, DATA_TYPE.indexOf(")"));
                DATA_TYPE = DATA_TYPE.substring(0, DATA_TYPE.indexOf("("));
            }
            String DATA_DEFAULT = rs.getString("DEFAULT");
            String NULLABLE = rs.getString("NULL");
            String COMMENTS = rs.getString("COMMENT");
            String PRIMARY_KEY = rs.getString("KEY");
            map.put("COLUMN_NAME", COLUMN_NAME);
            map.put("DATA_TYPE", DATA_TYPE);
            map.put("DATA_LENGTH", DATA_LENGTH);
            map.put("DATA_DEFAULT", DATA_DEFAULT == null ? "" : DATA_DEFAULT);
            map.put("NULLABLE", NULLABLE);
            map.put("COMMENTS", COMMENTS == null ? "" : COMMENTS);
            map.put("PRIMARY_KEY", "PRI".equals(PRIMARY_KEY));
            columnList.add(map);
            log.info("COLUMN_NAME=>{} DATA_TYPE=>{} DATA_LENGTH=>{}NULLABLE=>{}COMMENTS=>{}PRIMARY_KEY=>{}",
                    COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE, COMMENTS, PRIMARY_KEY);
        }
        rs.close();
        ps.close();
        return columnList;
    }


    @Override
    public String tableNames() {
        return "SHOW TABLE STATUS";
    }

    @Override
    public String tableStructure(String tableName) {
        return "SHOW FULL COLUMNS FROM " + tableName;
    }

}
