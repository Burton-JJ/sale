package com.burton.sale.enums;

import lombok.Getter;

/**
 * @author Burton
 * @title: OrderStatusEnum
 * @projectName sale
 * @description: 支付状态枚举
 * @date 2019/5/28 20:55
 */
@Getter
public enum PayStatusEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer payStatusCode;

    private String payStatusMsg;

    //构造方法默认pravite修饰
    PayStatusEnum(Integer code, String message) {
        this.payStatusCode = code;
        this.payStatusMsg = message;
    }
}
