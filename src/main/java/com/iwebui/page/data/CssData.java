package com.iwebui.page.data;

import org.openqa.selenium.By;

/**
 * 被测页面的元素对象：元素定位
 * @autho czy
 * @date 2020/8/25
 */
public class CssData {

    /**
     * css定位
     */
    public static final  By CLICK_XINWEN = By.cssSelector("#app > div > div.login-type-box > div.d-flex.j-sb.a-center > div:nth-child(1) > img");
    public static final  By PWDLOGIN = By.cssSelector("#app > div > div.login-box > div.form-box.pl-50.pr-50.pt-34 > div.d-flex.j-sb.color-them.mb-34 > span:nth-child(2)");
    public static final  By CLICK_NAME = By.cssSelector("#app > div > div.login-box > div.form-box.pl-50.pr-50.pt-34 > div.tel-login > form > div > div:nth-child(1) > div > div > div > input");
    public static final  By CLICK_PWD = By.cssSelector("#app > div > div.login-box > div.form-box.pl-50.pr-50.pt-34 > div.tel-login > form > div > div:nth-child(2) > div > div > div > input");
    public static final By LOGINBUTTON = By.cssSelector("#app > div > div.login-box > div.form-box.pl-50.pr-50.pt-34 > button");
}
