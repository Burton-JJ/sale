package com.burton.sale.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Burton
 * @title: OrderStatusEnum
 * @projectName sale
 * @description: 订单状态枚举
 * @date 2019/5/28 20:55
 */
@Getter
public enum OrderStatusEnum {

    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCLE(2, "取消");

    private Integer orderStatusCode;

    private String orderStatusMsg;

    //构造方法默认pravite修饰
    OrderStatusEnum(Integer code, String message) {
        this.orderStatusCode = code;
        this.orderStatusMsg = message;
    }
}
