package com.iwebui.page.easypoihandle;

import com.iwebui.base.BaseBrowser;
import com.iwebui.dto.LoginCaseDto;
import com.iwebui.dto.LoginUrlDto;
import com.iwebui.page.data.AccountData;
import com.iwebui.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BaiDuCaseEasypoiHandle extends BaseBrowser {
    /**
     * 进入被测页面
     */
    public void serchBaidu(WebDriver driver) {
        log.info("开始进入被测页面");
        enterPage(driver,AccountData.BAIDUURL);
    }
    /**
     * 进行读取报个测试用例进行测试并批量写入实际测试结果，和多sheet表之间的关联
     */
    public void loginCase(WebDriver driver) {
        UIElementUtil.clickButton("百度登录","点击右上角登录按钮",driver);
        UIElementUtil.clickButton("百度登录","点击账号密码登录按钮",driver);
        String excelCasePath = (String) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.cases");
        List<LoginCaseDto> loginDatas = EasyPoiUtil.importExcel(excelCasePath,0,1,1, LoginCaseDto.class);
        List<LoginUrlDto> urlDatas = EasyPoiUtil.importExcel(excelCasePath,1,1,1, LoginUrlDto.class);
        for (LoginCaseDto loginData : loginDatas ) {
            if (loginData.getUrlid().equalsIgnoreCase("1")){
                for (LoginUrlDto urlDto : urlDatas){
                    if (urlDto.getId().equalsIgnoreCase("1")){
                        loginData.setUrlpath(urlDto.getUrl());
                        System.out.println("被测访问URL路径：" +urlDto.getUrl());
                    }
                }
            }
            //由于EasyPoiUtil工具类对于空表格返回为null,sendKeys方法源码中不允许为null或0，这做下转换
            UIElementUtil.sendInput("百度登录","登录账号",driver,loginData.getName()==null? "" : loginData.getName());
            UIElementUtil.sendInput("百度登录","登录密码",driver,loginData.getPwd()==null? "" : loginData.getPwd());
            UIElementUtil.clickButton("百度登录","登录按钮",driver);
            String getResponseTip = driver.findElement(AccountData.TIPS).getText();
            loginData.setActual(getResponseTip);
            WebElement element = UIElementUtil.getElementByKeyword("百度登录","登录按钮",driver);
            AssertWebUtil.textToBePresentInElement(element,"期望结果",driver);
        }
        ExcelTestResultOutputUtil.exportSheet(loginDatas,urlDatas);
    }

}
