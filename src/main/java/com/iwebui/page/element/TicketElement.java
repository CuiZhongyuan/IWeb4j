package com.iwebui.page.element;

import com.iwebui.base.BaseBrowser;
import com.iwebui.page.data.CssData;
import com.iwebui.page.data.TestData;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;


/**
 * 被测页面操作层对象
 * @author czy
 * @date 2020/1/28
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
        log.info("跳转进入被测页面");
        super.enterPage(TestData.URL);
    }
    public void button() {
        clickButton(CssData.CLICK_XINWEN);
    }
}
