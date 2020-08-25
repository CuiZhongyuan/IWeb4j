package com.iwebui.business;



import com.iwebui.base.BaseTest;
import com.iwebui.utils.PrintscreenUtils;
import org.testng.annotations.Test;


/**
 * 业务层
 *
 * @author czy
 * @version 1.0.0
 * @date 2020/8/1 23:26
 */
@Test
public class TestCase extends BaseTest {

    //进入被测网页
    public void enterWeb()  {
        driver.get("https://github.com/");
        //截图
        PrintscreenUtils printscreenUtils = new PrintscreenUtils();
        printscreenUtils.printscreenUtils(driver);
        System.out.println("========测试通过======");
        driver.quit();
    }

}
