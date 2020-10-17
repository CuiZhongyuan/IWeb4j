package com.iwebui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AssertWebUtil {
    public  static WebDriverWait wait;
    /**
     *断言当前页面是否包含url
     */
    public static void urlContains(String inurlContains, WebDriver driver){
         wait = new WebDriverWait(driver,20);
        boolean urlContains = true;
        try{
            wait.until(ExpectedConditions.urlContains(inurlContains));

        }catch (Exception e){
            urlContains = false;
        }
        Assert.assertTrue(urlContains);
    }

    /**
     *断言文本值出现在指定的元素中
     */
    public static void textToBePresentInElement(WebElement element, String text,WebDriver driver){
        wait = new WebDriverWait(driver,20);
        boolean textToBePresentInElement = true;
        try{
            wait.until(ExpectedConditions.textToBePresentInElement(element,text));
        }catch (Exception e){
            textToBePresentInElement = false;
        }
        Assert.assertTrue(textToBePresentInElement);
    }
    /**
     *断言页面指定元素是否可点击
     */
    public static void elementToBeClickable(WebElement element,WebDriver driver){
        wait = new WebDriverWait(driver,20);
        boolean elementToBeClickable = true;
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e){
            elementToBeClickable = false;
        }
        Assert.assertTrue(elementToBeClickable);
    }

}
