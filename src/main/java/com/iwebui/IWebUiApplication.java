package com.iwebui;

import com.iwebui.utils.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

/**
 * @author czy-2020727
 */
@EnableConfigurationProperties
@SpringBootApplication
public class IWebUiApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(IWebUiApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }
}
