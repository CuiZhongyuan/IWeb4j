package com.webui.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class PrintscreenUtils {

    public void printscreenUtils(WebDriver driver){
        File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile,new File("src/main/resources/printscreen/shot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
