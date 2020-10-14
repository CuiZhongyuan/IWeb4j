package com.iwebui.listener;

import com.iwebui.base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;
/**
 * 集成allure报告
 *
 * @author czy
 * @date 2020/7/15
 */
public class TestFailListener extends TestListenerAdapter {
    public void onTestFailure(ITestResult result) {
        System.out.println("------ 当前测试方法： " + result.getMethod().getMethodName() + "执行失败...-----------");
        System.out.println(result.getMethod().getMethodName() + "失败!请稍后查看异常信息");
        super.onTestFailure(result);
        BaseTest testBase = (BaseTest) result.getInstance();
        //获取驱动
        WebDriver driver = testBase.driver;
        // 在报告中附加屏幕截图
        saveFailureScreenShot(driver);
        System.out.println("结束");
    }
    @Attachment(value = "失败截图如下：",type = "image/png")
    public byte[] saveFailureScreenShot(WebDriver driver) {
        byte[] screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("失败截图记录如下：", new ByteArrayInputStream(screenshotAs));
        return screenshotAs;
    }
}
