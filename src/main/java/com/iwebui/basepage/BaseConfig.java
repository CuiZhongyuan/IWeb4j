package com.iwebui.basepage;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

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

    private String remoteIP="192.168.1.168";

    private String remotePort="4723";

    // todo : 其它配置参数可自定义

}
