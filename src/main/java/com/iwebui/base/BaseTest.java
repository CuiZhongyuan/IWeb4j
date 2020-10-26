package com.iwebui.base;

import com.iwebui.utils.WordartDisplayer;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

/**
 * 测试基类
 *
 * @author czy
 * @date 2019/3/8
 */
@Slf4j
@Component
@SpringBootTest
public class BaseTest  extends AbstractTestNGSpringContextTests{
    /**
     * 驱动基类
     */
    @Autowired
    private BaseChromeDriver baseDriver;
    /**
     * 驱动
     * 对外暴露
     */
    public WebDriver driver;
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
    @BeforeClass(alwaysRun = true)
    public void beforeTest() {
        /* 驱动配置 */
//        baseDriver = new BaseChromeDriver();
        driver = baseDriver.startBrowser();
    }
    /**
     * 执行一个测试用例之后执行
     */
    @AfterClass(alwaysRun = true)
    public void afterTest() throws InterruptedException {
        // 驱动退出关闭浏览器
        baseDriver.closeBrowser();
//        driver = null;
        // todo : 其他工具的释放操作（看需要）
    }
}