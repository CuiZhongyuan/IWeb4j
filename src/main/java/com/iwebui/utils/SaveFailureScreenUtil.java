package com.iwebui.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

public class SaveFailureScreenUtil {
    /**
     * allure报个截图方法
     * @author czy
     */
    @Attachment(value = "失败截图如下：",type = "image/png")
    public static byte[] saveFailureScreenShot(WebDriver driver) {
        byte[] screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("失败截图记录如下：", new ByteArrayInputStream(screenshotAs));
        return screenshotAs;
    }
    /**
     * 针对alert弹框截图的特殊操作，截取整个win屏幕
     * @author czy
     * */
    public static void alertScreen(WebDriver driver){
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            String imgPath = (String) LoadStaticConfigUtil.getCommonYml("imgscreen.alertimg");
            long data = DateUtils.getCurrentMillisecond();
            ImageIO.write(image, "png", new File(imgPath+"/"+data+"alert.png"));
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
