package com.burton.sale.enums;

import lombok.Getter;

/**
 * @author Burton
 * @title: ProductStatusEnum
 * @projectName sale
 * @description: 商品状态枚举
 * @date 2019/5/27 14:59
 */
@Getter
public enum ProductStatusEnum {

    UP(0, "上架"),
    DOWN(1, "下架");

    /**
     * 商品状态码
     */
    private Integer ProductStatusCode;

    /**
     * 描述
     */
    private String DescriptionMsg;

    ProductStatusEnum(Integer code, String message) {
       this.ProductStatusCode = code;
       this.DescriptionMsg = message;
    }
}
