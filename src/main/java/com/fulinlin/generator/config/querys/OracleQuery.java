package com.fulinlin.generator.config.querys;

import com.fulinlin.generator.config.IDbQuery;
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
 * @program: database-doc
 * @author: fulin
 * @create: 2019-01-13 14:59
 **/
@Slf4j
public class OracleQuery extends AbstractDbQuery implements IDbQuery {
    @Override
    public Map<String, Object> getTableList(Connection conn) throws SQLException {
        List<Map<String, Object>> tableList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(this.tableNames());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            String TABLE_NAME = rs.getString("TABLE_NAME");
            String COMMENTS = rs.getString("COMMENTS");
            map.put("TABLE_NAME", TABLE_NAME);
            map.put("COMMENTS", COMMENTS == null ? "" : COMMENTS);
            List<Map<String, Object>> columnList = getColumnList(conn, TABLE_NAME); //获取列
            map.put("COLUMNS", columnList);
            //if (TABLE_NAME.startsWith("SYS"))//过滤
            tableList.add(map);
            log.info("TABLE_NAME=>{}COMMENTS=>{}", TABLE_NAME, COMMENTS);
        }
        rs.close();
        ps.close();
        return generateMap(tableList);
    }

    @Override
    List<Map<String, Object>> getColumnList(Connection conn, String tableName) throws SQLException {
        List<Map<String, Object>> columnList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(this.tableStructure(tableName));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            String COLUMN_NAME = rs.getString("COLUMN_NAME");
            String DATA_TYPE = rs.getString("DATA_TYPE");//VARCHAR2
            String DATA_LENGTH = rs.getString("DATA_LENGTH");//200
            String DATA_DEFAULT = rs.getString("DATA_DEFAULT");
            String NULLABLE = rs.getString("NULLABLE");
            String COMMENTS = rs.getString("COMMENTS");
            String PRIMARY_KEY = rs.getString("PRIMARY_KEY");
            map.put("COLUMN_NAME", COLUMN_NAME);
            map.put("DATA_TYPE", DATA_TYPE);
            map.put("DATA_LENGTH", DATA_LENGTH);
            map.put("DATA_DEFAULT", DATA_DEFAULT == null ? "" : DATA_DEFAULT);
            map.put("NULLABLE", !"N".equals(NULLABLE));
            map.put("COMMENTS", COMMENTS == null ? "" : COMMENTS);
            map.put("PRIMARY_KEY", "true".equals(PRIMARY_KEY));
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
        return "SELECT * FROM user_tab_comments WHERE table_type='TABLE'";
    }

    @Override
    public String tableStructure(String tableName) {
        return "SELECT utc.table_name,utc.column_name,utc.data_type,utc.data_length,utc.data_default,utc.nullable,ucc.comments,p.PRIMARY_KEY FROM  user_tab_columns utc LEFT JOIN user_col_comments ucc ON utc.table_name = ucc.table_name AND  utc.column_name = ucc.column_name LEFT JOIN ( SELECT col.table_name table_name, col.column_name column_name, CASE con.constraint_type WHEN 'P' THEN    'true' ELSE\t'false' END PRIMARY_KEY FROM user_constraints con,user_cons_columns col WHERE con.constraint_name = col.constraint_name AND con.constraint_type = 'P') p ON utc.column_name = p.column_name AND p.table_name = utc.table_name WHERE utc.table_name =" + tableName;
    }


}
