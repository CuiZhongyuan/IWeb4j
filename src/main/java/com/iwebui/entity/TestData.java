package com.iwebui.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
*
*测试用例 --PO（Persistent Object）：持久化对象
*
*@author wz
*@date 2020-5-31 13:43:37
**/
@Setter
@Getter
@Entity
@Table(name = "testData")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@DynamicInsert
@DynamicUpdate
public class TestData {
        private static final long serialVersionUID = 1L;
        /**
         * page测试数据id
         */
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Id
        private Long id;
        /**
        * 被测PointId
        */
        @NotBlank(message = "pointId不能为空")
        private Long pointId ;
        /**
         * 绑定状态 0 正向测试 1 反向测试
         */
        private Integer caseStatus = 1;
        /**
         * testData测试数据1
         */
        private String name;
        /**
         * testData测试数据1
         */
        private String pwd;
        /**
         * page的期望测试结果
         */
        private String expect;
        /**
         * pagede的实际测试结果
         */
        private String actual;
        /**
         * remark备注信息
         */
        private String remark;
}