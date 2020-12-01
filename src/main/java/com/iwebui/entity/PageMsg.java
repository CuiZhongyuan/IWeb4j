package com.iwebui.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@Table(name = "pageMsg")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@DynamicInsert
@DynamicUpdate
public class PageMsg {
        private static final long serialVersionUID = 1L;
        /**
         * page的id
         */
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @NotBlank(message = "id不能为空")
        @Id
        private Long id;
        /**
        * 页面请求类型：get/post/put/delete/update
        */
        @NotBlank(message = "page不能为空")
        private String type ;
        /**
        * page请求地址address(对应-testData)
        */
        @NotBlank(message = "url地址不能为空")
        private String address;

}