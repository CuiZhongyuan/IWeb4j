package com.iwebui.business;



import com.iwebui.base.BaseTest;
import com.iwebui.handle.LoginHandle;
import com.iwebui.utils.PrintscreenUtils;
import org.testng.annotations.Test;


/**
 * 测试用例业务层
 * @author czy
 * @version 1.0.0
 * @date 2020/8/1 23:26
 */
@Test
public class TestCase extends BaseTest {

    private LoginHandle loginHandle;
    //进入被测网页
    public void enterWeb()  {
        //初始化被测页面
        loginHandle = new LoginHandle(driver);
        //进入登录页面
        loginHandle.searchPage();
        //开始输入账号密码登录
        loginHandle.enterPage();
    }

}
