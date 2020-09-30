package com.iwebui.testcase;


import com.iwebui.base.BaseTest;
import com.iwebui.page.element.TicketElement;
import org.testng.annotations.Test;


/**
 * 测试用例业务层
 * @author czy
 * @version 1.0.0
 * @date 2020/8/1 23:26
 */
@Test
public class TestCase extends BaseTest {

    private TicketElement ticketElement;
    //进入被测网页
    public void enterWeb()  {
        //初始化被测页面
        ticketElement = new TicketElement(driver);
        //进入首页页面
        ticketElement.searchPage();
        //点击新闻标签
        ticketElement.button();
    }

}
