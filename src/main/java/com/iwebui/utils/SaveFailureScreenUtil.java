package com.iwebui.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class SaveFailureScreenUtil {
    /**
     * allure截图工具
     *
     * @author czy
     */
    @Attachment(value = "失败截图如下：",type = "image/png")
    public static byte[] saveFailureScreenShot(WebDriver driver) {
        byte[] screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("失败截图记录如下：", new ByteArrayInputStream(screenshotAs));
        return screenshotAs;
    }
}
