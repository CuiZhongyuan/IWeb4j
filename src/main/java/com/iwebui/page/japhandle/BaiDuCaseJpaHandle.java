package com.iwebui.page.japhandle;


import com.iwebui.base.BaseBrowser;
import com.iwebui.base.BaseTest;
import com.iwebui.entity.Logincase;
import com.iwebui.entity.UrlMessage;
import com.iwebui.page.data.AccountData;
import com.iwebui.page.japhandle.dao.BaiDuLoginDao;
import com.iwebui.page.japhandle.dao.BaiDuUrlDao;
import com.iwebui.testcase.BaiduLoginCase;
import com.iwebui.utils.UIElementUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class BaiDuCaseJpaHandle extends BaseBrowser {
    @Autowired
    private BaiDuLoginDao baiDuLoginDao;
    @Autowired
    private BaiDuUrlDao baiDuUrlDao;

    public void serchBaidu(WebDriver driver){
        log.info("开始进入被测页面");
        enterPage(driver,AccountData.BAIDUURL);
    }

    public List<Logincase> getAll(WebDriver driver) {
        UIElementUtil.clickButton("百度登录","点击右上角登录按钮",driver);
        UIElementUtil.clickButton("百度登录","点击账号密码登录按钮",driver);
        List<Logincase> logincaseList = baiDuLoginDao.findAll();
        List<UrlMessage> urlMessageList = baiDuUrlDao.findAll();
        //2.使用自定义写的sql方法操作数据库
        Map<Long, UrlMessage> map = new HashMap<>();
        urlMessageList.forEach(urlMessage -> {
            map.put(urlMessage.getId(), urlMessage);
        });
        logincaseList.forEach(logincase -> {
            String address = "";
            String actual = "";
            if (logincase.getCaseStatus().equals(1)) {
                UrlMessage urlMessage = map.get(logincase.getUrlId());
                if (urlMessage != null) {
                    address = urlMessage.getAddress();
                }
                if (!address.equals("")) {
                    baiDuLoginDao.updateLogincase(address, logincase.getId());
                }
                UIElementUtil.sendInput("百度登录","登录账号",driver,logincase.getName());
                UIElementUtil.sendInput("百度登录","登录密码",driver,logincase.getPwd());
                UIElementUtil.clickButton("百度登录","登录按钮",driver);
                actual = driver.findElement(AccountData.TIPS).getText();
                baiDuLoginDao.updateActual(actual,logincase.getId());


            }else {
                UrlMessage urlMessage = map.get(logincase.getUrlId());
                address = urlMessage.getAddress();
                baiDuLoginDao.updateLogincase(address, logincase.getId());
            }
        });
        //1.问题遗留，使用自带的jap操作数据库save方法时，抛异常暂未解决
//        for (Logincase logincase : logincaseList) {
//            if (logincase.getCaseStatus().equals(1)) {
//                for (UrlMessage urlMessage : urlMessageList) {
//                    if (urlMessage.getId().equals(logincase.getUrlId())) {
//                        logincase.setUrlPath(urlMessage.getAddress());
//                        baiDuLoginDao.save(logincase);
//                    }
//                }
//            }
//        }
        return null;
    }
}