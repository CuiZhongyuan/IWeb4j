package com.iwebui.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginCaseDto {
    @Excel(name = "flag(0是反向，1是正向)",orderNum = "1",width = 20)
    private String flag;
    @Excel(name = "urlid(访问id)",orderNum = "2",width = 20)
    private String urlid;
    @Excel(name = "name(登录账号)",orderNum = "3",width = 20)
    private String name;
    @Excel(name = "pwd(登录密码)",orderNum = "4",width = 20)
    private String pwd;
    @Excel(name = "desc(期望提示语)",orderNum = "5",width = 40)
    private String desc;
    @Excel(name = "actual(实际测试结果)",orderNum = "6",width = 40 )
    private String actual;
    @Excel(name = "urlpath(被测路径)",orderNum = "7",width = 40 )
    private String urlpath;
}
