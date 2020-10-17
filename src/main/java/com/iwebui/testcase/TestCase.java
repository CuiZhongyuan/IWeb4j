package com.iwebui.testcase;


import com.iwebui.base.BaseTest;
import com.iwebui.listener.TestFailListener;
import com.iwebui.page.element.TicketElement;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


/**
 * 测试用例业务层
 * @author czy
 * @version 1.0.0
 * @date 2020/6/8 23:26
 */
@Listeners( TestFailListener.class)
public class TestCase<i> extends BaseTest {

    private TicketElement ticketElement;
    //进入被测网页
    @Severity( SeverityLevel.NORMAL)
    @Story("登录测试用例组")
    @Description("平台角色登录")
    @Test
    public void enterWeb()  {
        //初始化被测页面
        ticketElement = new TicketElement(driver);
        //进入登录页面
        ticketElement.searchPage();
        //选择不同角色-这里选项平台角色
//        ticketElement.button();
        //账号密码登录
//        ticketElement.login();
        //选择不同系统
//        ticketElement.enterRole();
    }
    @Severity( SeverityLevel.BLOCKER)
    @Link("http://cstrip.12301100.com/")
    @Story("接口测试响应数据")
    @Description("可用于接口测试展示响应参数")
    public void testInterface() {
        try {
            String url = "http://cstrip.12301100.com/";
            String header = "accept: application/json";
            String body = "{\"name\":\"13300000000\",\"pwd\":\"111111\"}";
            String response = "{\"status\":200,\"message\":\"success\"}";
            caseStep(url,header,body,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //测试步骤
    public void caseStep(String url, String header, String body, String response)
    {
        Allure.addAttachment("请求地址", url);
        Allure.addAttachment("请求头", header);
        Allure.addAttachment("请求体", body);
        Allure.addAttachment("请求响应", response);
    }
}
