package com.iwebui.base;

import com.iwebui.utils.LoadStaticConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

@Slf4j
public class BaseChromeDriver {

    /**
     * 谷歌驱动配置
     *
     * @author czy
     * @date 2019/3/8 18:52
     */
        /**
         * 启动本地 chrome
         */
        public WebDriver driver;
        public WebDriver startBrowser() {
            String browserType = (String) LoadStaticConfigUtil.getCommonYml("browser.browserType");
            String chromeDriverPath =  (String) LoadStaticConfigUtil.getCommonYml("browser.chromeDriverPath");
            int implicitlyWait = (int) LoadStaticConfigUtil.getCommonYml("browser.implicitlyWait");
            if (browserType.equalsIgnoreCase("chrome")) {
                System.out.println("启动新的配置文件谷歌chrome..");
                //该工具会下载最新的ChromeDriver，默认下载路径会打印出来，找到后替换自己用的ChromeDriver版本即可，不用再配置ChromeDriver的读取路径
//                WebDriverManager.chromedriver().setup();
                // 配置文件路径设置谷歌驱动
                System.setProperty("webdriver.chrome.driver",chromeDriverPath);
                /* 启动 WebDriver */
                driver = new ChromeDriver();
                /**
                 * 整体使用显示等来来处理页面元素及弹框加载处理，具体封装方法见BaseBrowser类中点击事件和输入文本事件处理
                 * */
                // 隐式等待，作用域是全局---后续每个点击事件都用显示等待封装方法，隐式等待不用也可以
                driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
                // 页面加载等待，页面加载时间可以测试页面的响应时间，可用于页面性能分析，实际web自动化中不使用该方法
//          driver.manage().timeouts().pageLoadTimeout(baseConfig.getPageLoadTimeout(),TimeUnit.SECONDS);
                // JS 等待，
//          driver.manage().timeouts().setScriptTimeout(baseConfig.getSetScriptTimeout(),TimeUnit.SECONDS);
            }else if (browserType.equalsIgnoreCase("firefox")){
                // 系统变量设置谷歌驱动
                System.setProperty("webdriver.genko.driver",(String) LoadStaticConfigUtil.getCommonYml("browser.chromeDriverPath"));
                /* 启动 WebDriver */
                driver = new FirefoxDriver();
            } else {
                System.out.println("暂不支持该类型浏览器，请检查配置");
                //todo 往下自己可以创建
            }
            /* 窗口最大化 */
            driver.manage().window().maximize();
            return driver;
        }
    /**
     * 关闭浏览器
     *
     * @throws
     */
    public void closeBrowser()  {
        // JS 显示弹出框表示测试结束
        ((JavascriptExecutor) driver).executeScript("alert('测试完成，浏览器将在5s后关闭！')");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        log.info( "浏览器已成功关闭！");
    }
}
