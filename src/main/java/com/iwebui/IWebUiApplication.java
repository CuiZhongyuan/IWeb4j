package com.iwebui;

import com.iwebui.utils.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author czy-2020727
 */
@SpringBootApplication
public class IWebUiApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(IWebUiApplication.class, args);
        SpringContextUtils.setApplicationContext(context);
    }
}
