package com.iwebui.utils;

import com.iwebui.dto.Page;
import com.iwebui.dto.UIElement;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class UIElementUtil {
    /**
     * 显示等待
     */
    private static WebDriverWait wait;
    public static Object getCommonYml(Object key){
        Resource resource = new ClassPathResource("/application-dev.yml");
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties =  yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties.get(key);
    }
    public static List<Page> pageDatas = new ArrayList<>();
    static {
        //通过yaml配置文件引入路径
        String xmlpathconfig = (String) getCommonYml("pagexml.xmlpathconfig");
        lodePages(xmlpathconfig);
    }
    /**
     * @param pagesxmlPath 需要加载的xml路径
     */
    public static void lodePages(String pagesxmlPath) {
        //解析xml获取解析器
        SAXReader reader = new SAXReader();
        InputStream in;
        try {
            in = new FileInputStream(new File(pagesxmlPath));
            //拿到一个document对象
            Document document = reader.read(in);
            //获取根节点元素--xml的第一个节点对象loginPage.xml的<Pages></Pages>
            Element root = document.getRootElement();
            List<Element> pages = root.elements("Page");
            //把每一个Page封装成一个对象
            for (Element pageElement : pages){
                String pageKeyword = pageElement.attributeValue("keyword");
                //获取<Page>元素下的每个子节点（每个子节点为UIElement元素保存至uiElements集合中）
                List<Element> uiElements = pageElement.elements("UIElement");
                //将每个<UIElement>元素解析封装成UIElement类型对象，并保存至uiElementsList集合中
                List<UIElement> uiElementsList = new ArrayList<UIElement>();
                for (Element uiElement : uiElements){
                    String uiElementKeyword = uiElement.attributeValue("keyword");
                    String by = uiElement.attributeValue("by");
                    String value = uiElement.attributeValue("value");
                    String operationType = uiElement.attributeValue("operationType");
                    UIElement uiEle = new UIElement(uiElementKeyword,by,value,operationType);
                    uiElementsList.add(uiEle);
                }
                Page page = new Page(pageKeyword,uiElementsList);
                pageDatas.add(page);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据页面关键字和元素关键字完成元素定位
     *
     * @param pageKeyword 页面关键字[keyword]：<Page keyword="登录页面">
     * @param uiElementKeyword UI元素关键字[keyword]：  <UIElement keyword="登录用户名"></UIElement>
     * @return WebElement 对象
     */
    public static WebElement getElementByKeyword(String pageKeyword, String uiElementKeyword, WebDriver driver) {
        WebElement element= null;
        for (Page page : pageDatas){
            //根据页面关键字[keyword]过滤找到页面对象： <Page keyword="登录页面">
            if (pageKeyword.equalsIgnoreCase(page.getKeyword())){
                List<UIElement> uiElements = page.getUiElements();
                for (UIElement uiElement :uiElements){
                    //根据元素关键字[keyword]过滤找页面元素对象: <UIElement keyword="登录用户名" by="cssSelector" value="input[placeholder='请输入用户名或手机号']"></UIElement>
                    if (uiElementKeyword.equalsIgnoreCase(uiElement.getKeyword())){
                        //获取元素的选择器信息
                        String by = uiElement.getBy();
                        String value = uiElement.getValue();
                        element = getVisibleElement(by,value,driver);
                        return element;
                    }
                }
            }
        }
        return element;
    }
    /**
     * @param by  选择器类型
     * @param value  选择器值
     */
    public static WebElement getVisibleElement(String by, String value,  WebDriver driver) {
        wait = new WebDriverWait(driver,20);
        By locator =null;
        WebElement element = null;
        if ("id".equalsIgnoreCase(by)){
           locator =  By.id(value);
        }else if ("name".equalsIgnoreCase(by)){
            locator = By.name(value);
        }else if ("className".equalsIgnoreCase(by)){
            locator = By.className(value);
        }else if ("tagName".equalsIgnoreCase(by)){
            locator = By.tagName(value);
        }else if ("linkText".equalsIgnoreCase(by)){
            locator = By.linkText(value);
        }else if ("partialLinkText".equalsIgnoreCase(by)){
            locator = By.partialLinkText(value);
        }else if ("cssSelector".equalsIgnoreCase(by)){
            locator = By.cssSelector(value);
        }else if ("xpath".equalsIgnoreCase(by)){
            locator = By.xpath(value);
        }else {
            System.out.println("=========暂不支持:By."+by+"改类型");
        }
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (Exception e){
            if (e instanceof TimeoutException){
                System.out.println("根据by：["+by+"],value["+value+"]定位超时");
            }else {
                e.printStackTrace();
            }
        }
        return element;
    }
    /**
     * 点击元素
     * @param pageKeyword 页面关键字
     * @param uiElementKeyword 元素关键字
     * @param driver 传入的驱动
     * @param content 输入的内容，支持多内容，可以键盘输入
     */
    public static WebElement sendInput(String pageKeyword, String uiElementKeyword, WebDriver driver, CharSequence... content) {
       WebElement inputElement = getElementByKeyword(pageKeyword,uiElementKeyword,driver);
        inputElement.clear();
        inputElement.sendKeys(content);
        return inputElement;
    }
    /**
     * 点击弹框类型按钮(该类型按钮点击后会加载新的模态框页面，需要使用js获取页面渲染完后结果在操作)
     * @param pageKeyword 页面关键字
     * @param uiElementKeyword 元素关键字
     * @param driver 传入的驱动
     * @param content 输入
     */
    public static WebElement clickPop(String pageKeyword, String uiElementKeyword, WebDriver driver, CharSequence... content) {
        wait = new WebDriverWait(driver, 20);
        try {
            String jsToBeExecute = "return document.readyState =='complete'";
            boolean isReady = (boolean) wait.until(ExpectedConditions.jsReturnsValue(jsToBeExecute));
            if (isReady) {
                long time1 = DateUtils.getCurrentMillisecond();
                WebElement buttonPopElement = getElementByKeyword(pageKeyword,uiElementKeyword,driver);
                buttonPopElement.click();
                log.info("["+uiElementKeyword+"]该点击事件耗时："+(DateUtils.getCurrentMillisecond()-time1)+"ms");
                return buttonPopElement;
            }
        } catch (Exception e) {
            System.out.println("================元素不存在或不可点击状态，请查看=================="+pageKeyword+"页面下的/"+uiElementKeyword+"该关键字对应的：value元素不存在");
        }
        return null;
    }
    /**
     * 点击元素
     * @param pageKeyword 页面关键字
     * @param uiElementKeyword 元素关键字
     * @param driver 传入的驱动
     */
    public static WebElement clickButton(String pageKeyword, String uiElementKeyword, WebDriver driver) {
        try{
            long time1 = DateUtils.getCurrentMillisecond();
            WebElement buttonElement =getElementByKeyword(pageKeyword,uiElementKeyword,driver);
            buttonElement.click();
            log.info("["+uiElementKeyword+"]该点击事件耗时："+(DateUtils.getCurrentMillisecond()-time1)+"ms");
            return buttonElement;
        }catch (Exception e) {
            System.out.println("================元素不存在或不可点击状态，请查看=================="+pageKeyword+"页面下的/"+uiElementKeyword+"该关键字对应的：value元素不存在");
            return null;
        }
    }
}
