package com.iwebui.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Page {
    /**
     * 元素关键字keyword，跟路径下的page关键字，一个page代表一个页面
     */
    private String keyword;
    /**
     * UIElement对象，对应该页面上的每个元素
     */
    private List<UIElement> uiElements;
    public Page(){
        super();
    }
    public Page(String keyword, List<UIElement> uiElement){
        super();
        this.keyword = keyword;
        this.uiElements = uiElement;
    }
}
