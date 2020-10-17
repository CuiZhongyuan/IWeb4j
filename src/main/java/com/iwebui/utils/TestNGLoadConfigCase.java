package com.iwebui.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
@Component
public class TestNGLoadConfigCase extends AbstractTestNGSpringContextTests {
    /**
     * 如果想要通过testNG加载yaml的配置文件需要继承AbstractTestNGSpringContextTests+@SpringBootTest+@Component注解才可以加载到配置信息
     * 如下：[特注：如果通过testng有返回值，配置信息是加载不到的，暂未找到解决办法]
     */
    @Value("${pagexml.test}")
    private  String test;
    @Value("${pagexml.config}")
    private  String config;
    @Test
    public void configLoad(){
        System.out.println(test);
        System.out.println(config);
    }
}
