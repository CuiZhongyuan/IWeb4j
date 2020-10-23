package com.iwebui.testcase;

import com.iwebui.base.BaseTest;
import com.iwebui.page.easypoihandle.BaiDuCaseEasypoiHandle;
import com.iwebui.page.japhandle.BaiDuCaseJpaHandle;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class BaiduLoginCase extends BaseTest {

    private BaiDuCaseEasypoiHandle baiDuCaseElement;
    @Autowired
    BaiDuCaseJpaHandle baiDuCaseJpaHandle;
    //进入被测网页
    @Severity( SeverityLevel.NORMAL)
    @Description("百度登录测试示例")
    @Test
    public void baiduLogin(){
        //初始化ticketElement，获取驱动
        baiDuCaseElement = new BaiDuCaseEasypoiHandle(driver);
        baiDuCaseElement.serchBaidu();
        //excel数据驱动测试-使用easypoi实现
        baiDuCaseElement.loginCase();
        //数据库驱动测试-使用springJPA实现
//        baiDuCaseJpaHandle.getAll();

    }
}
