package com.iwebui.base;

import lombok.Getter;
import lombok.Setter;

/**
 * BaseApp
 * @author czy
 * @version 1.0.0
 * @date 2020/8/1 23:28
 */

/**
 * 配置参数写死，其它类公用。目前无法通过YAML配置注入bean中，原因是testng套件不支持bean注入，所以定义一个基础配置BaseConfig
 * */
@Setter
@Getter
public class BaseConfig {

    // 驱动存放路径 target/classes/chromedriver/chromedriver.exe，同样可以把其它浏览器驱动放在这里
    private String chromeDriverPath = this.getClass().getClassLoader().getResource("chromedriver/chromedriver.exe").getPath();
    private String browserType = "chrome";
//    //隐式等待(s)
    private int implicitlyWait = 10;
//    //显示等待(s)
//    private int webDriverWait = 10;
//    //页面加载等待(s)
//    private int pageLoadTimeout = 15;
//    //JS 等待(s)
//    private int setScriptTimeout = 10;
    // todo : 其它配置参数可自定义

}
