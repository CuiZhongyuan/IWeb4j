package com.iwebui.page.element;

import com.iwebui.base.BaseBrowser;
import com.iwebui.dto.LoginDatas;
import com.iwebui.listener.TestFailListener;
import com.iwebui.page.data.CssData;
import com.iwebui.page.data.AccountData;
import com.iwebui.page.data.TextData;
import com.iwebui.page.data.YynCssData;
import com.iwebui.utils.EasyPoiUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.openqa.selenium.Keys.ENTER;
/**
 * 被测页面操作层对象
 * @author czy
 * @date 2019/3/8
 */
@Listeners( TestFailListener.class)
@Slf4j
public class TicketElement extends BaseBrowser {
    /**
     * 构造器 1
     *
     * @param driver 驱动
     */
    public TicketElement(WebDriver driver) {
        super(driver);
    }
    /**
     * 进入被测页面
     */
    public void searchPage() {
        log.info("开始进入被测页面");
        enterPage(AccountData.URL);
    }
    public void button() {
            clickButton(CssData.CLICK_XINWEN);
            Assert.assertTrue(true,"这个一个预期的角色登录错误");
    }

    public void login() {
//        clickButton(CssData.PWDLOGIN);
        sendInput(YynCssData.NAME, TextData.NAME);
        sendInput(YynCssData.PWD,TextData.PWD);
        clickButton(YynCssData.CLICK);
    }
    /**
     * 项目业务流程测试（不是示例）
     */
    public void enterRole() {
        String firstHandle =  driver.getWindowHandle();
        log.info("第一个页面的句柄："+firstHandle);
        //跳转第二个页面，选择景区票务链接
        clickButton(YynCssData.ROLE);
        //跳转第三个页面，分销业务链接
//        clickButton(YynCssData.FSALE);
        //获取所有句柄(如果点击过快，会获取不到点击后的句柄)
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (handle.equals(firstHandle)) {
                System.out.println("当前位置为第一个页面");
                continue;
            }
            driver.switchTo().window(handle);
            String windowHandle = driver.getTitle();
            if (windowHandle.equalsIgnoreCase("未来票房V2.0-景区平台")) {
                System.out.println("******" + driver.getWindowHandle());
                //点击委托景区
                log.info("点击委托景区");
                clickButton(YynCssData.SCENIC);
                System.out.println("*****" + driver.getWindowHandle());
                sendInput(YynCssData.SEARCH, TextData.SHILIN);
                WebElement enter = driver.findElement(YynCssData.SEARCH);
                enter.sendKeys(ENTER);
                //进入被搜索页面
                clickButton(YynCssData.ENTERSHILIN);
                //收起：景区列表菜单
                clickButton(YynCssData.PACKUP);
                //点击景区管理菜单
                clickButton(YynCssData.SICNICSPOT);
                //点击门票菜单
                clickButton(YynCssData.TICKET);
                //输入门票code
                //由于此处使用的iframe内联框架，需要先进入内层才能正常定位元素，F12可以查看iframe标签
                driver.switchTo().frame("iframe4");
                sendInput(YynCssData.TICKETCODE, TextData.CODE);
                clickButton(YynCssData.CODESEARCH);
                //点击弹框，再次进入第二个iframe内联框
                clickPop(YynCssData.TIME);
                //由于该弹框是div模拟框,需要先跳出第一个iframe，在切换该iframe内联框，defaultContent方法是返回首次打开的页面，如果不确定多个iframe嵌套情况，可以使用Selenium IDE录制后查看也可以
                driver.switchTo().defaultContent();
                driver.switchTo().frame(2);
                //时间控件使用js代码输入
//                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//                javascriptExecutor.executeScript("var element = document.getElementById('searchStartDate')"+"element.value='2020-10-13';");
                clickButton(YynCssData.ADD);
                List<WebElement> deleteElements = driver.findElements(YynCssData.DELETE);
                for (WebElement deleteElement : deleteElements ){
                    if (deleteElement.isDisplayed()){
                        deleteElement.click();
                        clickPop(YynCssData.SURE);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println("无可删除时段信息");
                    }
                }
                clickButton(YynCssData.SAVE);
            } else if (windowHandle.equals("未来票房V2.0-分销业务系统")) {
                log.info("分销点击委托景区");
//                clickButton(YynCssData.FSALESCENIC);
            }
        }
    }
    /**
     * 登录覆盖测试--execl数据源驱动示例
     */
    public void loginTestCase() {
        String loginDatasPath = this.getClass().getClassLoader().getResource("loginTest.xlsx").getPath();
        List<LoginDatas> loginDatas = EasyPoiUtil.importExcel("C:\\Users\\Administrator\\Desktop\\loginTest.xlsx",0,1, LoginDatas.class);
        //过滤Easypoi读取表格多出两行为空的数据
        List<LoginDatas> collect = loginDatas.stream().filter(loginData -> loginData.getCode() != null || loginData.getDesc() != null || loginData.getFlag() != null || loginData.getPwd() != null).collect(Collectors.toList());
        for (LoginDatas loginData :collect ){
            if (loginData.getFlag().equalsIgnoreCase("0")){
                sendInput(YynCssData.NAME, loginData.getCode()==null?"":loginData.getCode());
                sendInput(YynCssData.PWD,loginData.getPwd()==null?"":loginData.getPwd());
                clickButton(YynCssData.CLICK);
            }
            if (loginData.getFlag().equalsIgnoreCase("1")){
                System.out.println(loginData.getCode()==null?"":loginData.getCode());
                sendInput(YynCssData.NAME, loginData.getCode()==null?"":loginData.getCode());
                sendInput(YynCssData.PWD,loginData.getPwd()==null?"":loginData.getPwd());
                String handle = driver.getWindowHandle();
                clickButton(YynCssData.CLICK);
                Set<String> handles = driver.getWindowHandles();
                for (String h : handles){
                    System.out.println(h);
                }
                String descTips = driver.getTitle();
               Assert.assertEquals(descTips,loginData.getDesc());
            }
            System.out.println("flag:"+loginData.getFlag()+" code:"+loginData.getCode()+" pwd:"+loginData.getPwd()+" desc"+loginData.getDesc());
        }
    }
}
