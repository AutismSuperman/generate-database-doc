package com.fulinlin.generator.entity;

import com.baomidou.mybatisplus.generator.jdbc.DatabaseMetaDataWrapper;
import lombok.Data;

import java.util.Map;

@Data
public class TableInfo {

    private DatabaseMetaDataWrapper.Table table;

    private Map<String, DatabaseMetaDataWrapper.Column> columns;

}
