package com.iwebui.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Parameter;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.util.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;


import static io.qameta.allure.aspects.StepsAspects.getLifecycle;
import static io.qameta.allure.util.ResultsUtils.*;

@Listeners(com.iwebui.listener.AssertListener.class)
public class AssertWebUtil {
    public  static WebDriverWait wait;
    /**
     *断言当前页面是否包含url
     */
    public static void urlContains(String inurlContains, WebDriver driver){
        boolean urlContains = true;
        try{
            wait.until(ExpectedConditions.urlContains(inurlContains));
        }catch (Exception e){
            urlContains = false;
            SaveFailureScreenUtil.saveFailureScreenShot(driver);
        }
        Assert.assertTrue(urlContains);
    }

    /**
     *断言文本值出现在指定的元素中
     */
    public static void textToBePresentInElement(WebElement element, String text,WebDriver driver){
        boolean textToBePresentInElement = true;
        try{
            wait.until(ExpectedConditions.textToBePresentInElement(element,text));
        }catch (Exception e){
            textToBePresentInElement = false;
            //test body断言失败展示
            Allure.step("断言失败",Status.FAILED);
            //allure异常截图
            SaveFailureScreenUtil.saveFailureScreenShot(driver);
            //获取异常信息后添加至allure的test body中【异常信息封装成一个集合，这里需要转成String类型】
            Allure.addAttachment("异常信息打印：",RewriteAssertUtil.errors.toString());
        }
        RewriteAssertUtil.assertTrue(textToBePresentInElement);
        System.out.println(RewriteAssertUtil.errors);

    }
    /**
     *断言页面指定元素是否可点击
     */
    public static void elementToBeClickable(WebElement element,WebDriver driver){
        boolean elementToBeClickable = true;
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e){

            SaveFailureScreenUtil.saveFailureScreenShot(driver);
            elementToBeClickable = false;
        }
        Assert.assertTrue(elementToBeClickable);
    }

}
