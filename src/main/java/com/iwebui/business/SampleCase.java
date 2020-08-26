package com.iwebui.business;

import com.iwebui.base.BaseTest;
import com.iwebui.handle.LoginHandle;
import com.iwebui.handle.SampleHandle;
import org.testng.annotations.Test;

@Test
public class SampleCase extends BaseTest {
    private SampleHandle sampleHandle;
    //进入被测网页
    public void enterWeb()  {
        //初始化被测页面
        sampleHandle = new SampleHandle(driver);
        //进入登录页面
        sampleHandle.searchPage();
        //开始输入账号密码登录
        sampleHandle.enterPage();
    }
}
