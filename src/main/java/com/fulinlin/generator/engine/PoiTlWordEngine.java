package com.fulinlin.generator.engine;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.fulinlin.generator.config.FileConfig;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class PoiTlWordEngine {


    public void outputFile(Map<String, Object> data, FileConfig fileConfig) throws IOException {
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder()
                .bind("columns", policy)
                .useSpringEL()
                .build();
        ClassPathResource classPathResource = new ClassPathResource(fileConfig.getTemplate());
        InputStream inputStream = classPathResource.getInputStream();
        XWPFTemplate.compile(inputStream, config)
                .render(data)
                .writeToFile(fileConfig.getWriteFile());
    }


}
