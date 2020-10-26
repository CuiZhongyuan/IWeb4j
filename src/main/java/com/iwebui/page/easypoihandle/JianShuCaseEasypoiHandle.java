package com.iwebui.page.easypoihandle;

import com.iwebui.base.BaseBrowser;
import com.iwebui.page.data.AccountData;
import com.iwebui.utils.UIElementUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JianShuCaseEasypoiHandle extends BaseBrowser {
    /**
     * 进入被测页面
     */
    public void searchJianshu(WebDriver driver) {
        log.info("开始进入被测页面");
        enterPage(driver,AccountData.JIANSHUURL);
    }
    public void jianshu(WebDriver driver) {
        UIElementUtil.sendInput("简书搜索功能测试","CodingTest",driver,"CodingTest");
        UIElementUtil.clickButton("简书搜索功能测试","搜索按钮",driver);
        //切换新的窗口
        switchNextHandle(driver);
        UIElementUtil.clickPop("简书搜索功能测试","点击CodingTest用户",driver);
    }
}
