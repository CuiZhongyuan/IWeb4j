package com.iwebui.page.element;

import org.openqa.selenium.By;

public class SampleElement {
    /**
     * 登录示例
     */
    public static final By LOGIN = By.xpath("//*[@id='login_field']");
    public static final By PWD = By.xpath("//*[@id='password']");
    public static final By BUTTON = By.xpath("//*[@id='login']/form/div[4]/input[9]");
}
