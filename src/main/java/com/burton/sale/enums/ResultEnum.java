package com.burton.sale.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Burton
 * @title: ResultEnum
 * @projectName sale
 * @description:异常枚举
 * @date 2019/5/29 13:58
 */
@Getter
public enum ResultEnum {

    PARAMS_ERROR("SALEE0001","参数校验失败{}"),
    PRODUCT_NOT_EXIST("SALEE0001","商品不存在"),
    STOCK_NOT_ENOUGH("SALEE0002","商品库存不足"),
    STOCK_DECREASE_FAIL("SALEE0003","扣减库存失败"),
    ORDER_NOT_EXIST("SALEE0004","订单不存在"),
    ORDER_DETAIL_NOT_EXIST("SALEE0005","订单详情不存在"),
    NEW_ORDER_CANCLE("SALEE0006","非新订单不可取消"),
    ORDER_CANCLE_FAIL("SALEE0007","订单取消失败"),
    OLDSTOCK_NOT_ENOUGH("SALEE0008","原商品库存不足"),
    STOCK_INCREASE_FAIL("SALEE0009","增加库存失败"),
    NEW_ORDER_FINISH("SALEE0010","非新订单不可完结"),
    ORDER_FINISH_FAIL("SALEE0011","订单完结失败"),
    NEW_ORDER_PAY("SALEE0012","非新订单不可支付"),
    ORDER_PAY_FAIL("SALEE0013","订单支付失败"),
    WAIT_PAY_ORDER_PAY("SALEE0014","非待支付订单不可支付"),
    JSON_TO_OBJECT_FAIL("SALEE0015","JSON字符串转对象失败"),
    CART_EMPTY("SALEE0016","购物车为空"),
    ;

    /**
     * 异常码
     */
    private String code;

    /**
     * 异常信息
     */
    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据错误码查找枚举
     * @param code
     * @return
     */
    public static ResultEnum find(String code) {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (resultEnum.getCode().equals(code)) {
                return resultEnum;
            }
        }
        return null;
    }
}
