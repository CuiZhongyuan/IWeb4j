package com.iwebui.page.lyEducation.service.impl;

import com.iwebui.base.BaseBrowser;
import com.iwebui.entity.PageMsg;
import com.iwebui.page.lyEducation.dao.LyPublicClassDao;
import com.iwebui.page.lyEducation.service.LyPublicClassService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class LyPublicClassServiceImpl extends BaseBrowser implements LyPublicClassService {
    @Autowired
    LyPublicClassDao lyPublicClassDao;

    @Override
    public void startUrl(WebDriver driver) {
        String urlStart = "";
        List<PageMsg> pageMsgList = lyPublicClassDao.findAll();
        //取出被测网址
        for (PageMsg pageMsgs : pageMsgList){
            if (pageMsgs.getId() == 1){
                urlStart = pageMsgs.getAddress();
                log.info("开始进入被测页面");
                enterPage(driver,urlStart);
            }else{
                System.out.println("被测网址不存在请检查");
            }
        }
    }
}
