package com.fulinlin.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @program: database-doc
 * @author: fulin
 * @create: 2019-01-13 15:12
 **/
@Data
@Accessors(chain = true)
public class FileConfig {
    /**
     * 默认模版路径
     */
    String templatesDir = "/templates";
    /**
     * 默认模版名称
     */
    String templateName = "default.xml";
    /**
     * 输出文件的地址
     */
    String outDir;
    /**
     * 数据的文件名称
     */
    String outFileName;
}
