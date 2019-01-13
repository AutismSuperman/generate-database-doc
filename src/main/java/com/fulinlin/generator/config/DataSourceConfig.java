package com.fulinlin.generator.config;

import com.fulinlin.generator.config.querys.MsqlQuery;
import com.fulinlin.generator.config.querys.OracleQuery;
import com.fulinlin.generator.enums.DbType;
import com.fulinlin.generator.exception.NoDatabaseExistsException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>
 * 数据库配置
 * </p>
 *
 * @author fulin
 * @since 2016/8/30
 */
@Data
@Accessors(chain = true)
public class DataSourceConfig {
    /**
     * 数据库连接用户名
     */
    private String username;
    /**
     * 数据库连接密码
     */
    private String password;
    /**
     * 驱动连接的URL
     */
    private String url;
    /**
     * 驱动名称
     */
    private String driverName;

    /**
     * PostgreSQL schemaName
     */
    private String schemaName;
    //*****************************************************************************/

    /**
     * 数据库信息查询
     */
    private IDbQuery dbQuery;
    /**
     * 数据库类型
     */
    private DbType dbType;


    public IDbQuery getDbQuery() {
        if (null == dbQuery) {
            switch (getDbType()) {
                case ORACLE:
                    dbQuery = new OracleQuery();
                    break;
                case MARIADB:
                    dbQuery = new MsqlQuery();//衍生mysql
                    break;
                default:
                    dbQuery = new MsqlQuery();//默认mysql
                    break;
            }
        }
        return dbQuery;
    }

    /**
     * 判断数据库类型
     *
     * @return 类型枚举值
     */
    private DbType getDbType() {
        if (null == dbType) {
            if (driverName.contains("mysql")) {
                dbType = DbType.MYSQL;
            } else if (driverName.contains("oracle")) {
                dbType = DbType.ORACLE;
            } else if (driverName.contains("postgresql")) {
                dbType = DbType.POSTGRE_SQL;
            } else if (driverName.contains("sqlserver")) {
                dbType = DbType.SQL_SERVER;
            } else if (driverName.contains("mariadb")) {
                dbType = DbType.MARIADB;
            } else {
                throw new NoDatabaseExistsException("Unknown type of database!");
            }
        }
        return dbType;
    }

    /**
     * 创建数据库连接对象
     *
     * @return Connection
     */
    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
