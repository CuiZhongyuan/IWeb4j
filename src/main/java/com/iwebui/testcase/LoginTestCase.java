package com.iwebui.testcase;

import cn.hutool.http.server.HttpServerResponse;
import com.iwebui.base.BaseTest;
import com.iwebui.listener.TestFailListener;
import com.iwebui.page.element.TicketElement;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletResponse;

/**
 * 数据驱动示例--Excel数据源驱动测试示例
 * */
@Listeners( TestFailListener.class)
public class LoginTestCase extends BaseTest {

    private TicketElement ticketElement;
    //进入被测网页
    @Severity( SeverityLevel.NORMAL)
    @Story("Excel数据源驱动示例")
    @Description("登录场景覆盖测试")
    @Test
    public void login(){
        //初始化ticketElement，获取驱动
        ticketElement = new TicketElement(driver);
        ticketElement.searchPage();
        ticketElement.loginTestCase();
    }
}
