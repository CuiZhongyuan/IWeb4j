package com.iwebui.entity;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
*被测url信息 --实体类校验
*
*@author czy
*@date 2020-5-31 13:43:37
**/
@StaticMetamodel( TestData.class)
public class TestData_ {
    public static volatile SingularAttribute<TestData,Long> id;
    public static volatile SingularAttribute<TestData,Integer> caseStatus;
    public static volatile SingularAttribute<TestData,Long> pointId;
    public static volatile SingularAttribute<TestData,String> name;
    public static volatile SingularAttribute<TestData,String> pwd;
    public static volatile SingularAttribute<TestData,String> expect;
    public static volatile SingularAttribute<TestData,String> actual;
    public static volatile SingularAttribute<TestData,String> remark;

}
