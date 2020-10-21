package com.iwebui.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginUrlDto {
    @Excel(name = "id（访问测试类型）",orderNum = "1",width = 20)
    private String id;
    @Excel(name = "type(请求类型)",orderNum = "2",width = 20)
    private String type;
    @Excel(name = "url(访问地址)",orderNum = "3",width = 40)
    private String url;
}
