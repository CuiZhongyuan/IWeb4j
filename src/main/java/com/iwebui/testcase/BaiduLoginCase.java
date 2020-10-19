package com.iwebui.testcase;

import com.iwebui.base.BaseTest;
import com.iwebui.page.element.BaiDuCaseElement;
import com.iwebui.page.element.JianShuCaseElement;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class BaiduLoginCase extends BaseTest {

    private BaiDuCaseElement baiDuCaseElement;
    //进入被测网页
    @Severity( SeverityLevel.NORMAL)
    @Description("百度登录测试示例")
    @Test
    public void login(){
        //初始化ticketElement，获取驱动
        baiDuCaseElement = new BaiDuCaseElement(driver);
        baiDuCaseElement.serchBaidu();
        baiDuCaseElement.loginCase();
    }
}
