package com.iwebui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class BaseChromeDriver {

    /**
     * 谷歌驱动配置
     *
     * @author czy
     * @date 2020/7/29 18:52
     */
        /**
         * 启动本地 chrome
         */
        public WebDriver driver;
        public WebDriver startBrowser(BaseConfig baseConfig) {

            /* 驱动配置进环境变量 */
            // 系统变量设置谷歌驱动
            System.setProperty("webdriver.chrome.driver", baseConfig.getChromeDriverPath());
            /* 启动 WebDriver */
            driver = new ChromeDriver();
            // 隐式等待
            driver.manage().timeouts().implicitlyWait(baseConfig.getImplicitlyWait(),TimeUnit.SECONDS);
            // 页面加载等待
            driver.manage().timeouts().pageLoadTimeout(baseConfig.getPageLoadTimeout(),TimeUnit.SECONDS);
            // JS 等待
            driver.manage().timeouts().setScriptTimeout(baseConfig.getSetScriptTimeout(),TimeUnit.SECONDS);
            /* 窗口最大化 */
            driver.manage().window().maximize();
            return driver;
        }

}
