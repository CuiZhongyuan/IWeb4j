package com.iwebui.handle;

import com.iwebui.base.BaseBrowser;
import com.iwebui.page.data.SampleData;
import com.iwebui.page.element.SampleElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

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
//        clickButton(SampleElement.BUTTON2);
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 进入被测景区
     */
    public void xiyouodng() {
        log.info("查找西游洞");
        super.enterPage(SampleData.URL1);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickButton(SampleElement.MENU);

//        sendInput(SampleElement.MENU2,SampleData.AREA);
        clickButton(SampleElement.LI_XIYOUDONG);
//        clickButton(SampleElement.LI_XIYOUDONG1);
        clickButton(SampleElement.LI_MENU);
        clickButton(SampleElement.LI_ticket);
        sendInput(SampleElement.LI_ticket1,SampleData.TICKETNAME);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickButton(SampleElement.LI_ticket2);
    }


}
