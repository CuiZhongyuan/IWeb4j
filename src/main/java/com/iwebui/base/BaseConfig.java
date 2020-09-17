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

    // 驱动存放路径 target/classes/chromedriver/chromedriver.exe
    private String chromeDriverPath=this.getClass().getClassLoader().getResource("chromedriver/chromedriver.exe").getPath();
    //隐式等待(s)
    private int implicitlyWait = 5;
    //显示等待(s)
    private int webDriverWait = 5;
    //页面加载等待(s)
    private int pageLoadTimeout = 5;
    //JS 等待(s)
    private int setScriptTimeout = 5;
    // todo : 其它配置参数可自定义

}
