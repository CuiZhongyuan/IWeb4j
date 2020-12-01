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
*URL访问路径 --PO（Persistent Object）：持久化对象
*
*@author wz
*@date 2020-5-31 13:43:37
**/
@Setter
@Getter
@Entity
@Table(name = "pagePoint")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@DynamicInsert
@DynamicUpdate
public class PagePoint {
        private static final long serialVersionUID = 1L;
        /**
         * pagePoint的id
         */
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @NotBlank(message = "id不能为空")
        @Id
        private Long id;
        /**
        * 对应被测pageId
        */
        @NotBlank(message = "pageId不能为空")
        private String pageId;
        /**
         * remark备注信息
         */
        @NotBlank(message = "remark备注信息")
        private String remark;

}