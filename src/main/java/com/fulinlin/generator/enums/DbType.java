package com.fulinlin.generator.enums;

public enum DbType {
    MYSQL("mysql", "MySql数据库"),
    MARIADB("mariadb", "MariaDB数据库"),
    ORACLE("oracle", "Oracle数据库"),
    POSTGRE_SQL("postgresql", "Postgre数据库"),
    SQL_SERVER2005("sqlserver2005", "SQLServer2005数据库"),
    SQL_SERVER("sqlserver", "SQLServer数据库"),
    OTHER("other", "其他数据库");
    private final String db;
    private final String desc;

    DbType(String db, String desc) {
        this.db = db;
        this.desc = desc;
    }

    public static DbType getDbType(String dbType) {
        DbType[] dts = values();
        DbType[] var2 = dts;
        int var3 = dts.length;
        for (int var4 = 0; var4 < var3; ++var4) {
            DbType dt = var2[var4];
            if (dt.getDb().equalsIgnoreCase(dbType)) {
                return dt;
            }
        }
        return OTHER;
    }

    public String getDb() {
        return this.db;
    }

    public String getDesc() {
        return this.desc;
    }
}
