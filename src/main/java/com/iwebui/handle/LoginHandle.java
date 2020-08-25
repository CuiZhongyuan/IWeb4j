package com.iwebui.handle;

import com.iwebui.base.BaseBrowser;
import com.iwebui.page.data.TestData;
import com.iwebui.page.element.LoginElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;


/**
 * 被测页面操作层对象
 * @author czy
 * @date 2020/1/28
 */
@Slf4j
public class LoginHandle extends BaseBrowser {

    /**
     * 构造器 1
     *
     * @param driver 驱动
     */
    public LoginHandle(WebDriver driver) {
        super(driver);
    }

    /**
     * 进入被测页面
     */
    public void searchPage() {
        log.info("跳转进入被测页面");
        super.enterPage(TestData.URL);
    }

    /**
     * 定位输入框输入账号密码登录
     */
    public void enterPage(){
        log.info("开始登录");
        clickButton(LoginElement.SEARCH_INPUT);
        clickButton(LoginElement.PWD);
        sendInput(LoginElement.INPUT_NAME,TestData.TEXT_NAME);
        sendInput(LoginElement.INPUT_PWD,TestData.TEXT_PWD);
        clickButton(LoginElement.BUTTON);
    }

}
