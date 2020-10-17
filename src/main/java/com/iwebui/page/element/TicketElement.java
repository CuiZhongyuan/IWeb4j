package com.iwebui.page.element;

import com.iwebui.base.BaseBrowser;
import com.iwebui.dto.EasyPoiDatas;
import com.iwebui.page.data.CssData;
import com.iwebui.page.data.AccountData;
import com.iwebui.page.data.TextData;
import com.iwebui.page.data.YynCssData;
import com.iwebui.utils.EasyPoiUtil;
import com.iwebui.utils.UIElementUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.openqa.selenium.Keys.ENTER;
/**
 * 被测页面操作层对象
 * @author czy
 * @date 2019/3/8
 */
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
        String loginDatasPath = "C:\\Users\\Administrator\\Desktop\\11.xls";
        List<EasyPoiDatas> loginDatas = EasyPoiUtil.importExcel(loginDatasPath,1,1, EasyPoiDatas.class);
        //过滤Easypoi读取表格多出两行为空的数据
        List<EasyPoiDatas> loginDatasNotEmPty = loginDatas.stream().filter(loginData -> loginData.getCode() != null || loginData.getDesc() != null || loginData.getFlag() != null || loginData.getPwd() != null).collect(Collectors.toList());
        //新的集合存放新的测试数据和测试结果
        List<EasyPoiDatas> collectS = new ArrayList<>();
        for (EasyPoiDatas loginData :loginDatasNotEmPty ) {
            try {
                //由于EasyPoiUtil工具类对于空表格返回为null,sendKeys方法源码中不允许为null或0，这做下转换
                UIElementUtil.sendInput("登录页面","登录用户名",driver,loginData.getCode() == null ? "" : loginData.getCode());
                UIElementUtil.sendInput("登录页面","登录密码",driver,loginData.getPwd() == null ? "" : loginData.getPwd());
                UIElementUtil.clickButton("登录页面","登入按钮",driver);
                String getResponseTip = driver.findElement(YynCssData.TIPS).getText();
                loginData.setActual(getResponseTip);
            } catch (Exception e) {
//                System.out.println("异常");
                e.printStackTrace();
            }
            collectS.add(loginData);
        }
        if (collectS.size() == 0){
            System.out.println("测试用例无数据，请查看");
        }else {
//        loginDatas.addAll(collectS);
            //1.读取原始的excel文件  数据存入coll
            //2.读取新的excel文件 执行业务 拼装数据 存入到 原始的coll
            //3.将所有的coll 覆盖原excel文件，loginDatas可以保留原始记录并在原始记录下写入新的数据
            EasyPoiUtil.exportExcel(collectS,"测试用例集","登录用例", EasyPoiDatas.class,loginDatasPath, true);
        }
    }
}
