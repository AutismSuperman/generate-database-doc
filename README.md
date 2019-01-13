# database-doc

#### 介绍
平时领导让写数据库文档，烦死了，于是就写了个基于freemarker 数据库生成word的小玩意。
目前还有点不完善，日后在更新

#### 使用说明
```java
        //***************数据库配置***************/
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig
                .setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("123456")
                .setUrl("jdbc:mysql://127.0.0.1:3306/dbm");
        //***************文件输出配置***************/
        FileConfig fileConfig = new FileConfig();
        fileConfig.setOutDir("D:/").setOutFileName("测试");
        //***************生成配置***************/
        GenerateConfig generateConfig = new GenerateConfig();
        generateConfig.setDataSourceConfig(dataSourceConfig).setFileConfig(fileConfig).execute();
```

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request
