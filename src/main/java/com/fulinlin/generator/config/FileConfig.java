package com.fulinlin.generator.config;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;
import lombok.Data;

/**
 * @program: database-doc
 * @author: fulin
 * @create: 2019-01-13 15:12
 **/
@Data
public class FileConfig {

    /**
     * 默认模版名称
     */
    private String template;
    /**
     * 数据的文件名称
     */
    private String writeFile;


    public static class Builder implements IConfigBuilder<FileConfig> {
        private final FileConfig fileConfig;

        public Builder() {
            this.fileConfig = new FileConfig();
        }


        public Builder template( String template) {
            this.fileConfig.template = template;
            return this;
        }

        public Builder writeFile( String writeFile) {
            this.fileConfig.writeFile = writeFile;
            return this;
        }

        @Override
        public FileConfig build() {
            return this.fileConfig;
        }
    }


}
