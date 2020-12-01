package com.iwebui.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
*被测url信息 --实体类校验
*
*@author czy
*@date 2020-5-31 13:43:37
**/
@StaticMetamodel( PageMsg.class)
public class PageMsg_ {
    public static volatile SingularAttribute<PageMsg,Long> id;
    public static volatile SingularAttribute<PageMsg,String> type;
    public static volatile SingularAttribute<PageMsg,String> address;
}
