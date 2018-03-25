package com.mind.search;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyunlong on 18-3-14.
 */
public class GeneratorExecution {
    public static void generator(){

        List<String> warnings=new ArrayList<String>();
        try {
            //		导入配置表mybatis-generator.xml
            File configFile=new File("mybatis-generator.xml");
            InputStream in = new ClassPathResource("").getClassLoader().getResourceAsStream("mybatis-generator.xml");

            //		解析
            ConfigurationParser cp=new ConfigurationParser(warnings);
            Configuration config=cp.parseConfiguration(in);
            //		是否覆盖
            DefaultShellCallback dsc=new DefaultShellCallback(true);
            MyBatisGenerator mg=new MyBatisGenerator(config, dsc, warnings);
            mg.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        GeneratorExecution.generator();
        System.out.println("done!");
    }
}
