package com.iwebui.handle;

import com.iwebui.base.BaseBrowser;
import com.iwebui.page.data.SampleData;
import com.iwebui.page.data.TestData;
import com.iwebui.page.element.LoginElement;
import com.iwebui.page.element.SampleElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class SampleHandle extends BaseBrowser {
    /**
     * 构造器 1
     *
     * @param driver 驱动
     */
    public SampleHandle(WebDriver driver) {
        super(driver);
    }

    /**
     * 进入被测页面
     */
    public void searchPage() {
        log.info("跳转进入被测页面");
        super.enterPage(SampleData.URL);
    }

    /**
     * 定位输入框输入账号密码登录
     */
    public void enterPage(){
        log.info("开始登录");
        sendInput(SampleElement.LOGIN, SampleData.TEXT_NAME);
        sendInput(SampleElement.PWD,SampleData.TEXT_PWD);
        clickButton(SampleElement.BUTTON);
    }

}
