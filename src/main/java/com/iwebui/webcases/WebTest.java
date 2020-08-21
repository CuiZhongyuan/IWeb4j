package com.iwebui.webcases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebTest {


    @Test
    public static void baiducase() {
        //设置系统变量（浏览器驱动名称，相对路径）
        System.setProperty("webdriver.chrome.driver","F:\\00_3IdeaProjects\\gitHubers\\WebUiProject\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        //实例化WebDriver,启动浏览器
        WebDriver driver = new ChromeDriver();
        //在浏览器中输入网址，并打开网页
        driver.get("https://www.baidu.com/");
        //找到要操作的元素,该元素的id是sign_up
//        WebElement welcome =driver.findElement(By.id("sign_up"));
//        WebElement shuru = driver.findElement(By.xpath("//input[contains(@class,'ipt')]"));
//        WebElement shuru = driver.findElement(By.xpath("//input[substring(@class,3)='ipt']"));
        WebElement shuru = driver.findElement(By.xpath("//*[@id=\"s-top-left\"]/a[2]"));
        //*[@id="s-top-left"]/a[1]
        shuru.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭浏览器
//        driver.quit();
        //关闭浏览器驱动
//        driver.close();
    }

   @Test
    public static void testcase() {
        //设置系统变量（浏览器驱动名称，相对路径）
        System.setProperty("webdriver.chrome.driver","F:\\00_3IdeaProjects\\gitHubers\\WebUiProject\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        //实例化WebDriver,启动浏览器
        WebDriver driver = new ChromeDriver();
        //在浏览器中输入网址，并打开网页
        driver.get("https://www.jianshu.com/");
        //找到要操作的元素,该元素的id是sign_up
//        WebElement welcome =driver.findElement(By.id("sign_up"));
       WebElement shuru = driver.findElement(By.name("q"));
       shuru.sendKeys("CodingTest");
       try {
           Thread.sleep(3000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       WebElement run = driver.findElement(By.className("search-btn"));
        //点击该元素
        run.click();
       try {
           Thread.sleep(5000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       //关闭浏览器
        driver.quit();
        //关闭浏览器驱动
//        driver.close();
    }


    @Test
    public void testcase1(){
        //设置系统变量（浏览器驱动名称，相对路径）
        System.setProperty("webdriver.chrome.driver","F:\\00_3IdeaProjects\\gitHubers\\WebUiProject\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        //实例化WebDriver,启动浏览器
        WebDriver driver = new ChromeDriver();
        //在浏览器中输入网址，并打开网页
        driver.get("https://www.baidu.com/");
        WebElement TT = driver.findElement(By.className("s_ipt"));
        TT.sendKeys("codingtest");
        WebElement t = driver.findElement(By.id("su"));
        t.click();
    }

}
