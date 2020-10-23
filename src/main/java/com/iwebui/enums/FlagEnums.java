package com.iwebui.enums;


/**
 * 驾驶员性别枚举
 *
 * @author wz
 * @date 2020/5/31 13:51
 */
public enum FlagEnums {
    /**
     * 正向用例
     */
    positive(0),

    /**
     * 反向用例
     */
    reverse(1);

    /**
     * 枚举值
     */
    private Integer value;


    public Integer getValue() {
        return value;
    }

    FlagEnums(Integer value){
        this.value = value;
    };
    public Object getViewName() {
        return viewNameOf(value);
    }

    public static Object viewNameOf(Integer value) {
        switch (value) {
            case 0:
                return "正向用例";
            case 1:
                return "反向用例";
            default:
                return "反向用例";
        }
    }
}
