package com.iwebui.testcase;

import com.iwebui.base.BaseTest;
import com.iwebui.page.easypoihandle.JianShuCaseEasypoiHandle;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * 数据驱动示例--Excel数据源驱动测试示例
 * */
public class JianShuSerchCase extends BaseTest {

    @Autowired
    private JianShuCaseEasypoiHandle caseElement;
    //进入被测网页
    @Severity( SeverityLevel.NORMAL)
    @Description("简书搜索测试示例")
    @Test
    public void jianshuSearch(){
        //继承父类获取驱动传递driver作用域
        caseElement.searchJianshu(driver);
        caseElement.jianshu(driver);
    }
}
