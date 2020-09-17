package com.iwebui.page.element;

import org.openqa.selenium.By;

public class SampleElement {
    /**
     * 登录示例--至操作页面
     */
    public static final By LOGIN = By.xpath("//*[@id='login_form']/div[1]/div/div/input");
    public static final By PWD = By.xpath("//*[@id='login_form']/div[2]/div/div/input");
    public static final By BUTTON = By.xpath("//*[@id='app']/div[2]/div/div[2]/div/button/span");
//    public static final By BUTTON2 = By.xpath("//*[@id='app']/div[3]/div/div[2]/div/div[1]/a/div/img");
    //跳转具体景区查询
    public static final By MENU = By.xpath("//*[@id='btn_1']");
//    public static final By MENU2 = By.xpath("//*[@id='companyName']");
    public static final By LI_XIYOUDONG = By.xpath("//*[@id='company']/li[13]");
//    public static final By LI_XIYOUDONG1 = By.xpath("//*[@id='company']/li");
    //点击基础信息菜单
    public static final By LI_MENU = By.xpath("//*[@id='side-menu']/li[2]/a/span[1]");
    //查找门票
    public static final By LI_ticket = By.xpath("//*[@id='side-menu']/li[2]/ul/li[5]/a");
    //门票搜索
    public static final By LI_ticket1 = By.xpath("//*[@id='searchticketCode']");
    //点击查询按钮
    public static final By LI_ticket2 = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/div[2]/div/div[2]/p/button[1]/strong']");
}
