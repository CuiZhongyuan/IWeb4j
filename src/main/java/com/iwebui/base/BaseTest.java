package com.iwebui.base;

import com.iwebui.utils.WordartDisplayer;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * 测试基类
 *
 * @author czy
 * @date 2020/1/22
 */
@Slf4j
public class BaseTest  {
    /**
     * 驱动基类
     */
    private BaseChromeDriver baseDriver;

    /**
     * 驱动
     * 对外暴露
     */
    public WebDriver driver;

    //注入配置文件
    BaseConfig baseConfig = new BaseConfig();
    /**
     * 执行一个测试套之前执行
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        // 显示文字 iwebui
        WordartDisplayer.display();
        // todo : 这里可以自己定制其他工具初始化操作（看需要）
    }

    /**
     * 执行一个测试用例之前执行
     * 这里做多线程的处理
     *
     */
    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        /* 驱动配置 */
        baseDriver = new BaseChromeDriver();
        driver = baseDriver.startBrowser(baseConfig);
    }
}