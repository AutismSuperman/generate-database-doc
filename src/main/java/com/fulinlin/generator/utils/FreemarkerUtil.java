package com.fulinlin.generator.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * FreemarkerUtil.getInstance().generateFile(
 * fileConfig.getTemplatesDir(),
 * fileConfig.getTemplateName(), map,
 * fileConfig.getOutDir(), fileName);
 * <p>
 * Description: HelloWorld！ <br/>
 * Autor: Created by fulin on 2016/12/1.
 */
@Deprecated
public class FreemarkerUtil {
    private static final FreemarkerUtil freemarkerUtil = new FreemarkerUtil();

    public static FreemarkerUtil getInstance() {
        return freemarkerUtil;
    }

    private FreemarkerUtil() {
    }

    /**
     * 获取模板
     *
     * @param templatesDir 例如"/templates"
     * @return
     */
    private Template getTemplate(String templatesDir, String name) {
        try {
            //通过Freemarker的Configuration读取相应的ftl
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            //设定去哪里读取相应的ftl模板文件
            cfg.setClassForTemplateLoading(this.getClass(), templatesDir);
            //在模板文件目录中找到名称为name的文件
            return cfg.getTemplate(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Description:  <br/>
     */
    public void generateFile(String templatesDir, String templateName, Map root, String outDir, String outFileName) {
        FileWriter out = null;
        try {
            //通过一个文件输出流，就可以写到相应的文件中
            out = new FileWriter(new File(outDir, outFileName));
            Template temp = this.getTemplate(templatesDir, templateName);
            Objects.requireNonNull(temp).process(root, out);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
