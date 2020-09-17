package com.iwebui.page.element;

import org.openqa.selenium.By;

/**
 * 被测页面的元素对象：元素定位
 * @autho czy
 * @date 2020/8/25
 */
public class LoginElement {

    /**
     * 角色选择定位
     */
    public static final  By CLICK_LOGIN1 = By.xpath("//*[@id='app']/div/div/div[2]/div[1]");

    public static final By CLICK_LOGIN2 = By.xpath("//*[@id='app']/div/div[2]/div[2]/div[1]/span[2]");

    public static final By INPUT_NAME = By.xpath("//*[@id='app']/div/div[2]/div[2]/div[2]/form/div/div[1]/div/div/div[1]/input");

    public static final By INPUT_PWD = By.xpath("//*[@id='app']/div/div[2]/div[2]/div[2]/form/div/div[2]/div/div/div/input");

    public static final By BUTTON = By.xpath("   //*[@id='app']/div/div[2]/div[2]/button");

}
