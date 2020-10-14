package com.iwebui.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginDatas {
    @Excel(name = "flag(是否是正向0是方向，1是正向)")
    private String flag;
    @Excel(name = "code(景区code作为登录账号)")
    private String code;
    @Excel(name = "pwd(登录密码)")
    private String pwd;
    @Excel(name = "desc(提示语)")
    private String desc;
}
