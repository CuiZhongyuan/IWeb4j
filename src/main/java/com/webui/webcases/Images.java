package com.webui.webcases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Images {

    @Test
    public void picDemo(){
        System.setProperty("webdriver.chrome.driver","F:\\00_3IdeaProjects\\gitHubers\\WebUiProject\\src\\main\\resources\\chromedriver\\chromedriver84.0.4147.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://testerhome.com");
        //截图
        File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile,new File("src/main/java/shot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
