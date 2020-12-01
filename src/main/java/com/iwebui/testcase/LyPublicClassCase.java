package com.iwebui.testcase;


import com.iwebui.base.BaseTest;
import com.iwebui.page.lyEducation.service.LyPublicClassService;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.iwebui.listener.AssertListener.class)
public class LyPublicClassCase extends BaseTest {
    @Autowired
    LyPublicClassService lyPublicClassService;
    //进入被测网页
    @Severity( SeverityLevel.NORMAL)
    @Description("ly-公开课测试-进入首页操作")
    @Test
    public void startUrl(){
        //打开被测网址
        lyPublicClassService.startUrl(driver);
    }

}
