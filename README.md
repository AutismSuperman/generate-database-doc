# generate-database-doc

## 介绍
平时领导让写数据库文档，烦死了，于是就写了个基于freemarker 数据库生成word的小玩意。
目前还有点不完善，日后在更新

### 使用说明
```java
    public static void main(String[] args) {
        GenerateConfig generateConfig = new GenerateConfig();
        generateConfig.setDataSourceConfig(new DataSourceConfig.Builder(
                        "jdbc:mariadb://127.0.0.1:3306/test",
                        "root",
                        "wangle")
                        //.schema("PUBLIC")                 
                        .dbQuery(new MariadbQuery())//根据不同的数据配置
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
```
### 生成效果

![show](https://raw.githubusercontent.com/AutismSuperman/generate-database-doc/master/image/show.png)

### 支持数据库

```java
    MYSQL("mysql", "MySql数据库"),
    MARIADB("mariadb", "MariaDB数据库"),
    ORACLE("oracle", "Oracle11g及以下数据库(高版本推荐使用ORACLE_NEW)"),
    ORACLE_12C("oracle12c", "Oracle12c+数据库"),
    DB2("db2", "DB2数据库"),
    H2("h2", "H2数据库"),
    HSQL("hsql", "HSQL数据库"),
    SQLITE("sqlite", "SQLite数据库"),
    POSTGRE_SQL("postgresql", "Postgre数据库"),
    SQL_SERVER2005("sqlserver2005", "SQLServer2005数据库"),
    SQL_SERVER("sqlserver", "SQLServer数据库"),
    DM("dm", "达梦数据库"),
    XU_GU("xugu", "虚谷数据库"),
    KINGBASE_ES("kingbasees", "人大金仓数据库"),
    PHOENIX("phoenix", "Phoenix HBase数据库"),
    GAUSS("zenith", "Gauss 数据库"),
    CLICK_HOUSE("clickhouse", "clickhouse 数据库"),
    GBASE("gbase", "南大通用(华库)数据库"),
    GBASE_8S("gbase-8s", "南大通用数据库 GBase 8s"),
    @Deprecated
    GBASEDBT("gbasedbt", "南大通用数据库"),
    @Deprecated
    GBASE_INFORMIX("gbase 8s", "南大通用数据库 GBase 8s"),
    SINODB("sinodb", "星瑞格数据库"),
    OSCAR("oscar", "神通数据库"),
    SYBASE("sybase", "Sybase ASE 数据库"),
    OCEAN_BASE("oceanbase", "OceanBase 数据库"),
    FIREBIRD("Firebird", "Firebird 数据库"),
    HIGH_GO("highgo", "瀚高数据库"),
    CUBRID("cubrid", "CUBRID数据库"),
    GOLDILOCKS("goldilocks", "GOLDILOCKS数据库"),
    CSIIDB("csiidb", "CSIIDB数据库"),
    SAP_HANA("hana", "SAP_HANA数据库"),
    IMPALA("impala", "impala数据库"),
    VERTICA("vertica", "vertica数据库"),
    XCloud("xcloud", "行云数据库"),
    REDSHIFT("redshift", "亚马逊redshift数据库"),
    OPENGAUSS("openGauss", "华为 opengauss 数据库"),
    TDENGINE("TDengine", "TDengine数据库"),
    INFORMIX("informix", "Informix数据库"),
    UXDB("uxdb", "优炫数据库"),
    LEALONE("lealone", "Lealone数据库");
```

### 感谢
[https://github.com/baomidou/mybatis-plus](https://github.com/baomidou/mybatis-plus)

