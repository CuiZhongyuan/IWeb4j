package com.iwebui.basepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.MalformedURLException;
import java.net.URL;


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
        WebDriver driver;
        public WebDriver startBrowser(BaseConfig baseConfig) {

            /* 驱动配置进环境变量 */
            // 系统变量设置谷歌驱动
            System.setProperty("webdriver.chrome.driver", baseConfig.getChromeDriverPath());
            /* 启动 WebDriver */
            driver = new ChromeDriver();
            return driver;
        }

        /**
         * 启动远端 chrome
         */
        public WebDriver startRemoteBrowser(BaseConfig baseConfig) {
            /* 驱动配置进环境变量 */
            // 系统变量设置谷歌驱动
            System.setProperty("webdriver.chrome.driver", baseConfig.getChromeDriverPath());
            /* 驱动可选项配置 */
            WebDriver driver = new org.openqa.selenium.chrome.ChromeDriver();
            /* 启动 RemoteWebDriver */
            URL url = null;
            try {
                url = new URL("http://" + baseConfig.getRemoteIP() + ":" + baseConfig.getRemotePort() + "/wd/hub/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }

}
