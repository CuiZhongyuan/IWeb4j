package com.iwebui.page.japhandle;


import com.iwebui.entity.Logincase;
import com.iwebui.entity.UrlMessage;
import com.iwebui.page.japhandle.dao.BaiDuLoginDao;
import com.iwebui.page.japhandle.dao.BaiDuUrlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@SpringBootTest
public class BaiDuCaseJpaHandle extends AbstractTestNGSpringContextTests {
    /**
     * 注意：要想通过testng把响应的bean注入，需要继承AbstractTestNGSpringContextTests类，且需要加上@SpringBootTest、@Component注解才能正常获取扫描到的bean对象
     */
    @Autowired
    private BaiDuLoginDao baiDuLoginDao;
    @Autowired
    private BaiDuUrlDao baiDuUrlDao;

    @Test
    public void tt() {
        System.out.println(getAll());
    }

    public List<Logincase> getAll() {
        List<Logincase> logincaseList = baiDuLoginDao.findAll();
        List<UrlMessage> urlMessageList = baiDuUrlDao.findAll();
        //2.使用自定义写的sql方法操作数据库
        Map<Long, UrlMessage> map = new HashMap<>();
        urlMessageList.forEach(urlMessage -> {
            map.put(urlMessage.getId(), urlMessage);
        });
        logincaseList.forEach(logincase -> {
            String address = "";
            if (logincase.getCaseStatus().equals(1)) {

                UrlMessage urlMessage = map.get(logincase.getUrlId());
                if (urlMessage != null) {
                    address = urlMessage.getAddress();
                }
                if (!address.equals("")) {
                    baiDuLoginDao.updateLogincase(address, logincase.getId());
                }
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