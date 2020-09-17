package com.iwebui.handle;

import com.iwebui.base.BaseBrowser;
import com.iwebui.page.data.TestData;
import com.iwebui.page.element.LoginElement;
import com.iwebui.page.element.TicketElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;


/**
 * 被测页面操作层对象
 * @author czy
 * @date 2020/1/28
 */
@Slf4j
public class TicketHandle extends BaseBrowser {

    /**
     * 构造器 1
     *
     * @param driver 驱动
     */
    public TicketHandle(WebDriver driver) {
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
        log.info("登录开始");
        clickButton(LoginElement.CLICK_LOGIN1);
        clickButton(LoginElement.CLICK_LOGIN2);
        sendInput(LoginElement.INPUT_NAME,TestData.TEXT_NAME);
        sendInput(LoginElement.INPUT_PWD,TestData.TEXT_PWD);
        clickButton(LoginElement.BUTTON);
    }
    /**
     * 门票菜单
     */
    public void ticketPage(){
        log.info("点击产品菜单");
        clickButton(TicketElement.TICKET1);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("点击门票菜单");
        clickButton(TicketElement.TICKET2);
//        clickButton(TicketElement.TICKET3);
    }


}
