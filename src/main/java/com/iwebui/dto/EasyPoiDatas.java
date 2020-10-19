package com.iwebui.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EasyPoiDatas {
    @Excel(name = "flag(0是反向，1是正向)",orderNum = "1")
    private String flag;
    @Excel(name = "name(登录账号)",orderNum = "2")
    private String name;
    @Excel(name = "pwd(登录密码)",orderNum = "3")
    private String pwd;
    @Excel(name = "desc(期望提示语)",orderNum = "4")
    private String desc;
    @Excel(name = "actual(实际测试结果)",orderNum = "5")
    private String actual;
}
