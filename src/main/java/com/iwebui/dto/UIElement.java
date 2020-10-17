package com.iwebui.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * UIElement类型，一个UIElement对应页面一个元素
 */
@Getter
@Setter
@ToString
public class UIElement {
    /**
     * 元素关键字
     */
    private String keyword;
    /**
     * 选择器类型，这里统一用By类型:By.id()/By.name/By.className/By.xpath/By.cssSelector
     * 通常web定位建议用By.cssSelector,更易用
     */
    private String by;
    /**
     * 选择器的值
     */
    private String value;
    /**
     * 选择器的值
     */
    private  String operationType;

    public UIElement(String keyword, String by, String value,String operationType) {
        super();
        this.keyword = keyword;
        this.by = by;
        this.value = value;
        this.operationType = operationType;
    }
    public UIElement(){
        super();
    }
}
