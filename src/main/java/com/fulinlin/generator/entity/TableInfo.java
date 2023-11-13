package com.fulinlin.generator.entity;

import com.baomidou.mybatisplus.generator.jdbc.DatabaseMetaDataWrapper;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TableInfo {

    private DatabaseMetaDataWrapper.Table table;

    private List<DatabaseMetaDataWrapper.Column> columns;

}
