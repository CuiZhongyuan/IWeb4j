package com.iwebui.business;



import com.iwebui.basepage.BaseConfig;
import com.iwebui.basepage.BaseChromeDriver;
import com.iwebui.utils.PrintscreenUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


/**
 * 业务层
 *
 * @author czy
 * @version 1.0.0
 * @date 2020/8/1 23:26
 */
@Test
public class TestCase {

    //引入驱动需要的配置参数
    BaseConfig baseConfig = new BaseConfig();
    //获取驱动
    WebDriver driver = new BaseChromeDriver().startBrowser(baseConfig);
    /**
     * 测试示例用例维护
    */
    //进入被测网页
    public void enterMini()  {
        driver.get("https://github.com/");
        //截图
        PrintscreenUtils printscreenUtils = new PrintscreenUtils();
        printscreenUtils.printscreenUtils(driver);
        System.out.println("========测试通过======");
    }

}
