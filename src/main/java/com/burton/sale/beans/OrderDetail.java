package com.burton.sale.beans;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情
 */
@Getter
@Setter
public class OrderDetail {
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

    private Date createTime;

    private Date updateTime;

}