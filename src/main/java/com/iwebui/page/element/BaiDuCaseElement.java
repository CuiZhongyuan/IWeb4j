package com.iwebui.page.element;

import com.iwebui.base.BaseBrowser;
import com.iwebui.dto.EasyPoiDatas;
import com.iwebui.page.data.AccountData;
import com.iwebui.utils.EasyPoiUtil;
import com.iwebui.utils.UIElementUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BaiDuCaseElement extends BaseBrowser {
    /**
     * 构造器 1
     *
     * @param driver 驱动
     */
    public BaiDuCaseElement(WebDriver driver) {
        super(driver);
    }

    /**
     * 进入被测页面
     */
    public void serchBaidu() {
        log.info("开始进入被测页面");
        enterPage(AccountData.BAIDUURL);
    }

    public void loginCase() {
        UIElementUtil.clickButton("百度登录","点击右上角登录按钮",driver);
        UIElementUtil.clickButton("百度登录","点击账号密码登录按钮",driver);
        String loginDatasPath = "src/main/resources/pagesxml/baidulogin.xls";
        List<EasyPoiDatas> loginDatas = EasyPoiUtil.importExcel(loginDatasPath,1,1, EasyPoiDatas.class);
        //过滤Easypoi读取表格多出两行为空的数据
        List<EasyPoiDatas> loginDatasNotEmPty = loginDatas.stream().filter(loginData -> loginData.getName() != null || loginData.getDesc() != null || loginData.getFlag() != null || loginData.getPwd() != null).collect(Collectors.toList());
        //新的集合存放新的测试数据和测试结果
        List<EasyPoiDatas> collectS = new ArrayList<>();
        for (EasyPoiDatas loginData :loginDatasNotEmPty ) {
            try {
                //由于EasyPoiUtil工具类对于空表格返回为null,sendKeys方法源码中不允许为null或0，这做下转换
                UIElementUtil.sendInput("百度登录","登录账号",driver,loginData.getName()==null? "" : loginData.getName());
                UIElementUtil.sendInput("百度登录","登录密码",driver,loginData.getPwd()==null? "" : loginData.getPwd());
                UIElementUtil.clickButton("百度登录","登录按钮",driver);
                String getResponseTip = driver.findElement(AccountData.TIPS).getText();
                loginData.setActual(getResponseTip);
            } catch (Exception e) {
                e.printStackTrace();
            }
            collectS.add(loginData);
        }
        if (collectS.size() == 0){
            System.out.println("测试用例无数据，请查看");
        }else {
            //3.将所有实际获取结果写入实际结果中
            EasyPoiUtil.exportExcel(collectS,"测试用例集","登录用例", EasyPoiDatas.class,loginDatasPath, true);
        }
    }

}
