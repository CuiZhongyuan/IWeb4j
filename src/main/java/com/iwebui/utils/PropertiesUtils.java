package com.iwebui.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Properties属性文件工具类
 *
 * @author lgl
 */
public class PropertiesUtils {

    private Properties props = null;

    /**
     * @param files
     */
    public void init(List<String> files) {
        /**
         * 得到config文件夹下所有property文件
         */
        props = new Properties();

        /**
         * 加载数据到props里
         */
        try {
            ClassPathResource res = null;
            InputStream in = null;
            for (String f : files) {
                res = new ClassPathResource(f);
                in = res.getInputStream();
                props.load(in);
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param filesName 文件名称数组 properties文件放在classpath
     * @param key       需要获取文件当中的键值
     * @return
     */
    public String getConfig(String filesName, String key) {
        if (null == props || 0 == props.size()) {
            init(getAllFiles(filesName));
        }
        return props.getProperty(key).trim();
    }

    /**
     * @param filesName
     * @return
     */
    private List<String> getAllFiles(String filesName) {
        List<String> initfiles = new ArrayList<String>();
        initfiles.add("config.properties");
        if (filesName != null && !"".equals(filesName)) {
            initfiles.add(filesName);
        }
        return initfiles;
    }
}