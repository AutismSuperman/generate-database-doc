package com.fulinlin.generator.entity;

import com.baomidou.mybatisplus.generator.jdbc.DatabaseMetaDataWrapper;
import lombok.Data;

import java.util.List;

@Data
public class TableInfo {

    /**
     * 表信息
     */
    private DatabaseMetaDataWrapper.Table table;

    /**
     * 列注释
     */
    private List<DatabaseMetaDataWrapper.Column> columns;

}
