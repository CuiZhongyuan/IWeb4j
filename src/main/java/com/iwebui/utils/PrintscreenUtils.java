package com.iwebui.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class PrintscreenUtils {
    DateUtils dateUtils = new DateUtils();
    public void printscreenUtils(WebDriver driver){
        String imgname = dateUtils.getTimeMillis();
        File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile,new File("src/main/resources/printscreen/"+imgname+"test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
