package com.iwebui.page.element;

import com.iwebui.base.BaseBrowser;
import com.iwebui.page.data.AccountData;
import com.iwebui.utils.UIElementUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class JianShuCaseElement extends BaseBrowser {
    /**
     * 构造器 1
     *
     * @param driver 驱动
     */
    public JianShuCaseElement(WebDriver driver) {
        super(driver);
    }
    /**
     * 进入被测页面
     */
    public void searchJianshu() {
        log.info("开始进入被测页面");
        enterPage(AccountData.JIANSHUURL);
    }
    public void jianshu() {
        String currentHandle = driver.getWindowHandle();
        UIElementUtil.sendInput("简书搜索功能测试","CodingTest",driver,"CodingTest");
        UIElementUtil.clickButton("简书搜索功能测试","搜索按钮",driver);
        //切换新的窗口
        switchNextHandle();
        UIElementUtil.clickPop("简书搜索功能测试","点击CodingTest用户",driver);
    }
}
