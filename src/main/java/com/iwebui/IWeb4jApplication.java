package com.iwebui;

import com.iwebui.utils.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

/**
 * @author czy-2020727
 */
@EnableConfigurationProperties
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class IWeb4jApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(IWeb4jApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }
}
