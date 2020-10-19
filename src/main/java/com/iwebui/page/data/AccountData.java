package com.iwebui.page.data;

import org.openqa.selenium.By;

/**
 * 测试页面对象：数据
 *
 * @authorCZY
 * @date 2020/8/25
 */

public class AccountData {
     //test网址
    public static final String URL = "http://127.0.0.1:8090/index";
    //简书地址
    public static final String JIANSHUURL = "https://www.jianshu.com/";
    //百度地址
    public static final String BAIDUURL = "https://www.baidu.com/";
    //登录提示语
    public static final By TIPS = By.cssSelector("#TANGRAM__PSP_11__error");

}
