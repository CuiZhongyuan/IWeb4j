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
    @Autowired
    private BaiDuCaseEasypoiHandle baiDuCaseEasypoiHandle;
    @Autowired
    BaiDuCaseJpaHandle baiDuCaseJpaHandle;

    /**
     * easypoi实现excel数据驱动
     */
    @Severity( SeverityLevel.NORMAL)
    @Description("百度登录测试示例--使用easypoi实现")
    @Test
    public void baibaiDuCaseEasypoiHandle(){
        //初始化ticketElement，获取驱动
        baiDuCaseEasypoiHandle.serchBaidu(driver);
        //excel数据驱动测试-使用easypoi实现
        baiDuCaseEasypoiHandle.loginCase(driver);
    }

    /**
     * JPA实现数据库数据驱动示例
     */
    @Severity( SeverityLevel.NORMAL)
    @Description("百度登录测试示例--使用JPA实现")
    @Test
    public void baiDuCaseJpaHandle(){
        //进入被测网址
        baiDuCaseJpaHandle.serchBaidu(driver);
        //数据库驱动，使用jpa实现
        baiDuCaseJpaHandle.getAll(driver);
    }
}
