package com.burton.sale.beans;

import com.burton.sale.enums.OrderStatusEnum;
import com.burton.sale.enums.PayStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主要信息
 */
@Getter
@Setter
public class OrderMaster {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /**
     * 订单状态默认为新订单
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getOrderStatusCode();
    /**
     * 支付状态默认为等待支付
     */
    private Integer payStatus = PayStatusEnum.WAIT.getPayStatusCode();

    private Date createTime;

    private Date updateTime;

}