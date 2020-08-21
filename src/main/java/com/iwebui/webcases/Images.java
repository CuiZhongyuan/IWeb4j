package com.iwebui.webcases;

import com.iwebui.utils.PrintscreenUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Images {

    @Test
    public void picDemo(){
        System.setProperty("webdriver.chrome.driver","F:\\00_3IdeaProjects\\gitHubers\\WebUiProject\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com/");
        //截图
        PrintscreenUtils printscreenUtils = new PrintscreenUtils();
        printscreenUtils.printscreenUtils(driver);
    }
}
